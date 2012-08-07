/**
 * Funf: Open Sensing Framework
 * Copyright (C) 2010-2011 Nadav Aharony, Wei Pan, Alex Pentland. 
 * Acknowledgments: Alan Gardner
 * Contact: nadav@media.mit.edu
 * 
 * This file is part of Funf.
 * 
 * Funf is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version. 
 * 
 * Funf is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with Funf. If not, see <http://www.gnu.org/licenses/>.
 */
package org.contextlogger.android;

import static edu.mit.media.funf.AsyncSharedPrefs.async;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import edu.mit.media.funf.IOUtils;
import edu.mit.media.funf.Utils;
import edu.mit.media.funf.configured.ConfiguredPipeline;
import edu.mit.media.funf.configured.FunfConfig;
import edu.mit.media.funf.probe.Probe;
import edu.mit.media.funf.storage.BundleSerializer;

public class MainPipeline extends ConfiguredPipeline {
	public static final String TAG = "FunfBGCollector";
	public static final String MAIN_CONFIG = "main_config";
	public static final String TOTAL_COUNT_KEY = "SCAN_COUNT";

	@Override
	public void onCreate() {
		super.onCreate();
		setEncryptionPassword("justdoit".toCharArray());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		super.onHandleIntent(intent);
	}

	@Override
	public BundleSerializer getBundleSerializer() {
		return new BundleToJson();
	}

	public static class BundleToJson implements BundleSerializer {
		public String serialize(Bundle bundle) {
			return JsonUtils.getGson().toJson(Utils.getValues(bundle));
		}
	}

	@Override
	public void onDataReceived(Bundle data) {
		super.onDataReceived(data);
		incrementTotalCount();
	}

	@Override
	public void onStatusReceived(Probe.Status status) {
		super.onStatusReceived(status);
	}

	@Override
	public void onDetailsReceived(Probe.Details details) {
		super.onDetailsReceived(details);
	}

	public static boolean isEnabled(Context context) {
		return getSystemPrefs(context).getBoolean(ENABLED_KEY, true);
	}

	@Override
	public SharedPreferences getSystemPrefs() {
		return getSystemPrefs(this);
	}

	public static SharedPreferences getSystemPrefs(Context context) {
		return async(context.getSharedPreferences(MainPipeline.class.getName()
				+ "_system", MODE_PRIVATE));
	}

	@Override
	public FunfConfig getConfig() {
		return getMainConfig(this);
	}

	public static FunfConfig getMainConfig(Context context) {
		FunfConfig config = getConfig(context, MAIN_CONFIG);
		if (config.getName() == null) {
			String jsonString = getStringFromAsset(context,
					"default_config.json");
			if (jsonString == null) {
				Log.e(TAG, "Error loading default config.  Using blank config.");
				jsonString = "{}";
			}
			try {
				config.edit().setAll(jsonString).commit();
			} catch (JSONException e) {
				Log.e(TAG, "Error parsing default config", e);
			}
		}
		return config;
	}

	public static String getStringFromAsset(Context context, String filename) {
		InputStream is = null;
		try {
			is = context.getAssets().open(filename);
			return IOUtils.inputStreamToString(is, Charset.defaultCharset()
					.name());
		} catch (IOException e) {
			Log.e(TAG, "Unable to read asset to string", e);
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					Log.e(TAG, "Unable to close asset input stream", e);
				}
			}
		}
	}

	public static long getScanCount(Context context) {
		return getSystemPrefs(context).getLong(TOTAL_COUNT_KEY, 0L);
	}

	private void incrementTotalCount() {
		boolean success = false;
		while (!success) {
			SharedPreferences.Editor editor = getSystemPrefs().edit();
			editor.putLong(TOTAL_COUNT_KEY, getScanCount(this) + 1L);
			success = editor.commit();
		}
	}
}
