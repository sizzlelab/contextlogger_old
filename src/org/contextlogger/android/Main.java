package org.contextlogger.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Main extends Activity implements OnSharedPreferenceChangeListener {

	private int mPos = 0;
	private String mSelectedActivity = "";
	private String mRunningActivity = "";
	private Context mContext = null;
	private boolean isActivityRunning = false;
	private TextView lbl_status = null;
	private TextView tv_message = null;
	private TextView tv_dataCount = null;
	private Button btn_toggle = null;
	private Spinner spinner = null;
	private Button btn_toggleAct = null;
	private static final String CUSTOM_INTENT_ACTION = "org.contextlogger.android.customIntentAction";
	private static final String COUNT_ACTION = "org.contextlogger.android.totalMessageCount";

	private final BroadcastReceiver tcReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle b = intent.getExtras();
			long tc = b.getLong("totalCount");
			tv_dataCount.setText("Total count: " + tc);
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		lbl_status = (TextView) findViewById(R.id.lbl_status);
		btn_toggle = (Button) findViewById(R.id.btn_toggle);
		btn_toggleAct = (Button) findViewById(R.id.btn_toggle_activity);
		tv_message = (TextView) findViewById(R.id.tv_message);
		tv_dataCount = (TextView) findViewById(R.id.dataCountText);
		addListenerOnSpinnerItemSelection();

		if (mContext != null) {
			mContext = getApplicationContext();
		}

		setLabels(savedInstanceState);
		MainPipeline.getSystemPrefs(this)
				.registerOnSharedPreferenceChangeListener(this);
		tv_dataCount.setText("Total count: " + MainPipeline.getScanCount(this));

		IntentFilter filter = new IntentFilter();
		filter.addAction(COUNT_ACTION);
		registerReceiver(tcReceiver, filter);
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString("mRunningActivity", mRunningActivity);
		savedInstanceState.putBoolean("isActivityRunning", isActivityRunning);
		super.onSaveInstanceState(savedInstanceState);
	}

	private void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById(R.id.activity_spinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String nowSelectedActivity = parent.getItemAtPosition(pos)
						.toString();
				if (isActivityRunning) {
					tv_message.setText("Stop activity '" + mSelectedActivity
							+ "' before changing to new activity.");
					spinner.setSelection(mPos);
				} else {
					mPos = pos;
					mSelectedActivity = nowSelectedActivity;
					tv_message.setText("");
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(tcReceiver);
	}

	private void setLabels(Bundle savedInstanceState) {
		boolean isServiceRunning = false;
		isServiceRunning = MainPipeline.isEnabled(this);

		if (savedInstanceState != null) {
			isActivityRunning = savedInstanceState
					.getBoolean("isActivityRunning");
			mRunningActivity = savedInstanceState.getString("mRunningActivity");
		}

		if (isServiceRunning) {
			lbl_status.setText(R.string.service_running);
			btn_toggle.setText(R.string.btn_stop_service);
		} else {
			lbl_status.setText(R.string.service_stopped);
			btn_toggle.setText(R.string.btn_start_service);
		}

		if (isActivityRunning && mRunningActivity.length() > 0) {
			ArrayAdapter<String> arrAdapter = (ArrayAdapter<String>) spinner
					.getAdapter();
			mPos = arrAdapter.getPosition(mRunningActivity.trim());
			spinner.setSelection(mPos);
			btn_toggleAct.setText(R.string.btn_stop_activity);
		}
	}

	private void setLabels(boolean value) {
		if (value) {
			lbl_status.setText(R.string.service_running);
			btn_toggle.setText(R.string.btn_stop_service);
			tv_message.setText("");

		} else {
			lbl_status.setText(R.string.service_stopped);
			btn_toggle.setText(R.string.btn_start_service);

			if (isActivityRunning) {
				Intent i = new Intent();
				i.setAction(CUSTOM_INTENT_ACTION);
				i.putExtra("APPLICATION_ACTION", mRunningActivity);
				i.putExtra("APPLICATION_DATA", "false");

				mContext = getApplicationContext();
				mContext.sendBroadcast(i);

				isActivityRunning = false;
				btn_toggleAct.setText(R.string.btn_start_activity);
				tv_message.setText("");
			}
		}
	}

	public void btnToggleClicked(View v) {
		boolean isRunning = MainPipeline.isEnabled(this);

		if (isRunning) {
			setLabels(false);
			Intent archiveIntent = new Intent(this, MainPipeline.class);
			archiveIntent.setAction(MainPipeline.ACTION_DISABLE);
			startService(archiveIntent);

			isRunning = MainPipeline.isEnabled(this);

		} else {
			setLabels(true);
			Intent archiveIntent = new Intent(this, MainPipeline.class);
			archiveIntent.setAction(MainPipeline.ACTION_ENABLE);
			startService(archiveIntent);
		}
	}

	public void btnActivityClicked(View v) {
		boolean isServiceRunning = MainPipeline.isEnabled(this);
		if (!isServiceRunning) {
			tv_message
					.setText("Start ContextLogger service by pressing above button 'Start service'.");
		} else {
			if (!isActivityRunning) {
				Intent i = new Intent();
				i.setAction(CUSTOM_INTENT_ACTION);
				i.putExtra("APPLICATION_ACTION", mSelectedActivity);
				i.putExtra("APPLICATION_DATA", "true");
				mContext = getApplicationContext();
				mContext.sendBroadcast(i);

				mRunningActivity = mSelectedActivity;
				isActivityRunning = true;

				btn_toggleAct.setText(R.string.btn_stop_activity);
			} else {
				Intent i = new Intent();
				i.setAction(CUSTOM_INTENT_ACTION);
				i.putExtra("APPLICATION_ACTION", mRunningActivity);
				i.putExtra("APPLICATION_DATA", "false");
				mContext = getApplicationContext();
				mContext.sendBroadcast(i);

				isActivityRunning = false;

				btn_toggleAct.setText(R.string.btn_start_activity);
			}
		}
	}

	public void btn_export_clicked(View v) {
		Intent archiveIntent = new Intent(this, MainPipeline.class);
		archiveIntent.setAction(MainPipeline.ACTION_ARCHIVE_DATA);
		startService(archiveIntent);
	}

	@Override
	protected void onResume() {
		boolean knownValue = isActivityRunning;
		boolean actualValue = false;
		if (!isActivityRunning) {
			String lastAction = ApplicationProbe.getLastAction();
			String lastActionData = ApplicationProbe.getLastActionData();
			if (lastAction != null && lastAction.length() > 0
					&& lastActionData.equals("true")) {
				mRunningActivity = lastAction;
				isActivityRunning = Boolean.valueOf(lastActionData);
				actualValue = isActivityRunning;
			}
		}

		if (knownValue == false && actualValue == true) {
			ArrayAdapter<String> arrAdapter = (ArrayAdapter<String>) spinner
					.getAdapter();
			mPos = arrAdapter.getPosition(mRunningActivity.trim());
			spinner.setSelection(mPos);
			btn_toggleAct.setText(R.string.btn_stop_activity);
		}

		super.onResume();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (MainPipeline.TOTAL_COUNT_KEY.equals(key)) {
			updateScanCount();
		}
	}

	private void updateScanCount() {
		Intent i = new Intent();
		i.setAction(COUNT_ACTION);
		i.putExtra("totalCount", MainPipeline.getScanCount(this));
		mContext = getApplicationContext();
		mContext.sendBroadcast(i);
	}
}