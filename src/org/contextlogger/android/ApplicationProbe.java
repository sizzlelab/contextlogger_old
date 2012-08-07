package org.contextlogger.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import edu.mit.media.funf.Utils;
import edu.mit.media.funf.probe.Probe;

/**
 * @author Nalin Chaudhary
 */
public class ApplicationProbe extends Probe implements ApplicationSensorKeys {

	private static final String CUSTOM_INTENT_ACTION = "org.contextlogger.android.customIntentAction";
	private ApplicationIntentReceiver receiver = null;
	private static String m_lAction = null;
	private static String m_lActData = null;
	
	@Override
	public String[] getRequiredPermissions() {
		return new String[]{};
	}

	@Override
	public String[] getRequiredFeatures() {
		return new String[]{};
	}

	@Override
	public Parameter[] getAvailableParameters() {
		return new Parameter[] {
				new Parameter(Parameter.Builtin.START, 0L),
				new Parameter(Parameter.Builtin.END, 0L)
			};
	}

	@Override
	protected void onEnable() {
		receiver = new ApplicationIntentReceiver();
		IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT_ACTION);
		registerReceiver(receiver, intentFilter);
	}

	@Override
	protected void onRun(Bundle params) {
	}

	@Override
	protected void onStop() {
	}

	@Override
	protected void onDisable() {
		unregisterReceiver(receiver);
	}

	@Override
	public void sendProbeData() {
	}

	private class ApplicationIntentReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			final String intentAction = intent.getAction();
			if (CUSTOM_INTENT_ACTION.equals(intentAction))
			{
				String appAction = intent.getExtras().getString("APPLICATION_ACTION");
				String appData = intent.getExtras().getString("APPLICATION_DATA");
				
				Bundle data = new Bundle();
				data.putString(APPLICATION_ACTION, appAction);
				data.putString(APPLICATION_DATA, appData);
				sendProbeData(Utils.getTimestamp(), data);
				
				m_lAction = appAction;
				m_lActData = appData;
			}
		}
	}
	
	public static String getLastAction() {
		return m_lAction;
	}

	public static String getLastActionData() {
		return m_lActData;
	}
}
