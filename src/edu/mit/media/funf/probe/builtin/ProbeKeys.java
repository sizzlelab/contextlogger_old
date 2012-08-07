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
package edu.mit.media.funf.probe.builtin;

import android.net.Uri;

public class ProbeKeys {
	
	public static interface StatusKeys {
		public static final String 
		ENABLED = "ENABLED";
		public static final String RUNNING = "RUNNING";
		public static final String NEXT_RUN = "NEXT_RUN";
		public static final String PREVIOUS_RUN = "PREVIOUS_RUN";
		public static final String NAME = "NAME";
		public static final String DISPLAY_NAME = "DISPLAY_NAME";
		public static final String REQUIRED_PERMISSIONS = "REQUIRED_PERMISSIONS";
		public static final String REQUIRED_FEATURES = "REQUIRED_FEATURES";
		public static final String PARAMETERS = "PARAMETERS";
	}
	
	public static interface BaseProbeKeys {
		public static final String 
		PROBE = "PROBE";
		public static final String // TODO: add probe version
		TIMESTAMP = "TIMESTAMP";
	}
	
	public static interface SensorKeys extends BaseProbeKeys {
		public static final String 
		MAXIMUM_RANGE = "MAXIMUM_RANGE";
		public static final String NAME = "NAME";
		public static final String POWER = "POWER";
		public static final String RESOLUTION = "RESOLUTION";
		public static final String TYPE = "TYPE";
		public static final String VENDOR = "VENDOR";
		public static final String VERSION = "VERSION";
		public static final String SENSOR = "SENSOR";
		public static final String EVENT_TIMESTAMP = "EVENT_TIMESTAMP";
		public static final String ACCURACY = "ACCURACY";
	}
	
	public static interface AccelerometerFeaturesKeys extends BaseProbeKeys {
		public static final String 
		DIFF_FRAME_SECS = "DIFF_FRAME_SECS";
		public static final String NUM_FRAME_SAMPLES = "NUM_FRAME_SAMPLES";
		public static final String MEAN = "MEAN";
		public static final String ABSOLUTE_CENTRAL_MOMENT = "ABSOLUTE_CENTRAL_MOMENT";
		public static final String STANDARD_DEVIATION = "STANDARD_DEVIATION";
		public static final String MAX_DEVIATION = "MAX_DEVIATION";
		public static final String PSD_ACROSS_FREQUENCY_BANDS = "PSD_ACROSS_FREQUENCY_BANDS";
	}
	
	public static interface AccelerometerSensorKeys extends SensorKeys {
		public static final String 
		X = "X";
		public static final String Y = "Y";
		public static final String Z = "Z";	
	}
	
	public static interface ActivityKeys extends BaseProbeKeys {
		public static final String 
		TOTAL_INTERVALS = "TOTAL_INTERVALS";
		public static final String LOW_ACTIVITY_INTERVALS = "LOW_ACTIVITY_INTERVALS";
		public static final String HIGH_ACTIVITY_INTERVALS = "HIGH_ACTIVITY_INTERVALS";
	}
	
	public static interface AndroidInfoKeys extends BaseProbeKeys {
		public static final String 
		FIRMWARE_VERSION = "FIRMWARE_VERSION";
		public static final String BUILD_NUMBER = "BUILD_NUMBER";
		public static final String SDK = "SDK";
	}
	
	public static interface ApplicationsKeys extends BaseProbeKeys {
		public static final String 
		INSTALLED_APPLICATIONS = "INSTALLED_APPLICATIONS";
		public static final String UNINSTALLED_APPLICATIONS = "UNINSTALLED_APPLICATIONS";
	}
	
	public static interface AudioFeaturesKeys extends BaseProbeKeys {
		public static final String 
		DIFF_SECS = "DIFF_SECS";
		public static final String L1_NORM = "L1_NORM";
		public static final String L2_NORM = "L2_NORM";
		public static final String LINF_NORM = "LINF_NORM";
		public static final String PSD_ACROSS_FREQUENCY_BANDS = "PSD_ACROSS_FREQUENCY_BANDS";
		public static final String MFCCS = "MFCCS";
	}
	
	public static interface AudioFilesKeys extends BaseProbeKeys, android.provider.MediaStore.Audio.AudioColumns {
		public static final String AUDIO_FILES = "AUDIO_FILES";
	}
	
	public static interface BatteryKeys extends BaseProbeKeys {
		public static final String 
		ICON_SMALL = "icon-small";
		public static final String PRESENT = "present";
		public static final String SCALE = "scale";
		public static final String LEVEL = "level";
		public static final String TECHNOLOGY = "technology";
		public static final String STATUS = "status";
		public static final String VOLTAGE = "voltage";
		public static final String HEALTH = "health";
		public static final String TEMPERATURE = "temperature";
	}
	
	public static interface BluetoothKeys extends BaseProbeKeys {
		public static final String 
		DEVICES = "DEVICES";
	}
	
	public static class BrowserBookmarksKeys extends android.provider.Browser.BookmarkColumns implements BaseProbeKeys {
		public static final String BOOKMARKS = "BOOKMARKS";
	}
	
	public static class BrowserSearchesKeys extends android.provider.Browser.SearchColumns implements BaseProbeKeys {
		public static final String SEARCHES = "SEARCHES";
	}
	
	public static class CallLogKeys extends android.provider.CallLog.Calls implements BaseProbeKeys {
		public static final String CALLS = "CALLS";
	}
	
	public static interface CellKeys extends BaseProbeKeys {
		public static final String
		TYPE = "type";
		// TODO: fill with cdma data keys
		// baseStationId
		// baseStationLatitude
		// baseStationLongitude
		// networkId
		// systemId
		public static final String PSC = "psc";
		public static final String CID = "cid";
		public static final String LAC = "lac";
	}
	
	public static interface ContactKeys extends BaseProbeKeys {
		public static final String CONTACT_DATA = "CONTACT_DATA";
		// Cannot extend android final class android.provider.ContactsContract.Data, or inherit from protected interfaces
	}
	
	public static interface GravitySensorKeys extends SensorKeys {
		public static final String 
		X = "X";
		public static final String Y = "Y";
		public static final String Z = "Z";	
	}
	
	public static interface GyroscopeSensorKeys extends SensorKeys {
		public static final String 
		X = "X";
		public static final String Y = "Y";
		public static final String Z = "Z";	
	}
	
	public static interface HardwareInfoKeys extends BaseProbeKeys {
		public static final String 
		WIFI_MAC = "WIFI_MAC";
		public static final String BLUETOOTH_MAC = "BLUETOOTH_MAC";
		public static final String ANDROID_ID = "ANDROID_ID";
		public static final String BRAND = "BRAND";
		public static final String MODEL = "MODEL";
		public static final String DEVICE_ID = "DEVICE_ID";	
	}
	
	public static interface ImagesKeys extends BaseProbeKeys, android.provider.MediaStore.Images.ImageColumns {
		public static final String 
		IMAGES = "IMAGES";	
	}
	
	public static interface LightSensorKeys extends SensorKeys {
		public static final String 
		LUX = "LUX";	
	}
	
	public static interface LinearAccelerationSensorKeys extends SensorKeys {
		public static final String 
		X = "X";
		public static final String Y = "Y";
		public static final String Z = "Z";	
	}
	
	public static interface LocationKeys extends BaseProbeKeys {
		public static final String 
		LOCATION = "LOCATION";
	}
	
	public static interface MagneticFieldSensorKeys extends SensorKeys {
		public static final String 
		X = "X";
		public static final String Y = "Y";
		public static final String Z = "Z";
	}
	
	public static interface OrientationSensorKeys extends SensorKeys {
		public static final String 
		AZIMUTH = "AZIMUTH";
		public static final String PITCH = "PITCH";
		public static final String ROLL = "ROLL";
	}
	
	public static interface PressureSensorKeys extends SensorKeys {
		public static final String 
		PRESSURE = "PRESSURE";
	}
	
	public static interface ProximitySensorKeys extends SensorKeys {
		public static final String 
		DISTANCE = "DISTANCE";
	}
	
	public static interface RotationVectorSensorKeys extends SensorKeys {
		public static final String 
		X_SIN_THETA_OVER_2 = "X_SIN_THETA_OVER_2";
		public static final String Y_SIN_THETA_OVER_2 = "Y_SIN_THETA_OVER_2";
		public static final String Z_SIN_THETA_OVER_2 = "Z_SIN_THETA_OVER_2";
		public static final String COS_THETA_OVER_2 = "COS_THETA_OVER_2";
	}
	
	public static interface RunningApplicationsKeys extends BaseProbeKeys {
		public static final String 
		RUNNING_TASKS = "RUNNING_TASKS";
	}
	
	public static interface ScreenKeys extends BaseProbeKeys {
		public static final String 
		SCREEN_ON = "SCREEN_ON";
	}
	
	public static interface SMSKeys extends BaseProbeKeys, AndroidInternal.TextBasedSmsColumns {
		public static final String 
		MESSAGES = "MESSAGES";
	}
	
	public static interface TelephonyKeys extends BaseProbeKeys {
		public static final String 
		CALL_STATE = "CALL_STATE";
		public static final String DEVICE_ID = "DEVICE_ID";
		public static final String DEVICE_SOFTWARE_VERSION = "DEVICE_SOFTWARE_VERSION";
		public static final String LINE_1_NUMBER = "LINE_1_NUMBER";
		public static final String NETWORK_COUNTRY_ISO = "NETWORK_COUNTRY_ISO";
		public static final String NETWORK_OPERATOR = "NETWORK_OPERATOR";
		public static final String NETWORK_OPERATOR_NAME = "NETWORK_OPERATOR_NAME";
		public static final String NETWORK_TYPE = "NETWORK_TYPE";
		public static final String PHONE_TYPE = "PHONE_TYPE";
		public static final String SIM_COUNTRY_ISO = "SIM_COUNTRY_ISO";
		public static final String SIM_OPERATOR = "SIM_OPERATOR";
		public static final String SIM_OPERATOR_NAME = "SIM_OPERATOR_NAME";
		public static final String SIM_SERIAL_NUMBER = "SIM_SERIAL_NUMBER";
		public static final String SIM_STATE = "SIM_STATE";
		public static final String SUBSCRIBER_ID = "SUBSCRIBER_ID";
		public static final String VOICEMAIL_ALPHA_TAG = "VOICEMAIL_ALPHA_TAG";
		public static final String VOICEMAIL_NUMBER = "VOICEMAIL_NUMBER";
		public static final String HAS_ICC_CARD = "HAS_ICC_CARD";
	}
	
	public static interface TemperatureSensorKeys extends SensorKeys {
		public static final String 
		TEMPERATURE = "TEMPERATURE";
	}
	
	public static interface TimeOffsetKeys extends BaseProbeKeys {
		public static final String 
		TIME_OFFSET = "TIME_OFFSET";
	}
	
	public static interface VideosKeys extends BaseProbeKeys, android.provider.MediaStore.Video.VideoColumns {
		public static final String 
		VIDEOS = "VIDEOS";	
	}
	
	public static interface WifiKeys extends BaseProbeKeys {
		public static final String 
		SCAN_RESULTS = "SCAN_RESULTS";
	}
	
	public static interface ServicesKeys extends BaseProbeKeys {
		public static final String 
		RUNNING_SERVICES = "RUNNING_SERVICES";
	}
	
	public static interface AccountsKeys extends BaseProbeKeys {
		public static final String 
		ACCOUNTS = "ACCOUNTS";
		public static final String NAME = "NAME";
		public static final String TYPE = "TYPE";
	}
	
	public interface AndroidInternal {
	
		///////////////////////////////////////////
		// COPIED FROM NON-PUBLIC ANDROID API
		// http://www.google.com/codesearch/p?hl=en#fxuXIzvA0aY/core/java/android/provider/Telephony.java&q=package:android%20%22core/java/android/provider/Telephony.java%22&sa=N&cd=1&ct=rc&l=186
	
		public interface Sms extends TextBasedSmsColumns {
			public static final Uri CONTENT_URI = Uri.parse("content://sms");
			public static final String MESSAGES = "MESSAGES";
			/* NOTE: other fields and methods not copied */
		}
		
		/**
	     * Base columns for tables that contain text based SMSs.
	     */
	    public interface TextBasedSmsColumns {
	        /**
	         * The type of the message
	         * <P>Type: INTEGER</P>
	         */
	        public static final String TYPE = "type";
	
	        public static final int MESSAGE_TYPE_ALL    = 0;
	        public static final int MESSAGE_TYPE_INBOX  = 1;
	        public static final int MESSAGE_TYPE_SENT   = 2;
	        public static final int MESSAGE_TYPE_DRAFT  = 3;
	        public static final int MESSAGE_TYPE_OUTBOX = 4;
	        public static final int MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
	        public static final int MESSAGE_TYPE_QUEUED = 6; // for messages to send later
	
	
	        /**
	         * The thread ID of the message
	         * <P>Type: INTEGER</P>
	         */
	        public static final String THREAD_ID = "thread_id";
	
	        /**
	         * The address of the other party
	         * <P>Type: TEXT</P>
	         */
	        public static final String ADDRESS = "address";
	
	        /**
	         * The person ID of the sender
	         * <P>Type: INTEGER (long)</P>
	         */
	        public static final String PERSON_ID = "person";
	
	        /**
	         * The date the message was sent
	         * <P>Type: INTEGER (long)</P>
	         */
	        public static final String DATE = "date";
	
	        /**
	         * Has the message been read
	         * <P>Type: INTEGER (boolean)</P>
	         */
	        public static final String READ = "read";
	
	        /**
	         * Indicates whether this message has been seen by the user. The "seen" flag will be
	         * used to figure out whether we need to throw up a statusbar notification or not.
	         */
	        public static final String SEEN = "seen";
	
	        /**
	         * The TP-Status value for the message, or -1 if no status has
	         * been received
	         */
	        public static final String STATUS = "status";
	
	        public static final int STATUS_NONE = -1;
	        public static final int STATUS_COMPLETE = 0;
	        public static final int STATUS_PENDING = 32;
	        public static final int STATUS_FAILED = 64;
	
	        /**
	         * The subject of the message, if present
	         * <P>Type: TEXT</P>
	         */
	        public static final String SUBJECT = "subject";
	
	        /**
	         * The body of the message
	         * <P>Type: TEXT</P>
	         */
	        public static final String BODY = "body";
	
	        /**
	         * The id of the sender of the conversation, if present
	         * <P>Type: INTEGER (reference to item in content://contacts/people)</P>
	         */
	        public static final String PERSON = "person";
	
	        /**
	         * The protocol identifier code
	         * <P>Type: INTEGER</P>
	         */
	        public static final String PROTOCOL = "protocol";
	
	        /**
	         * Whether the <code>TP-Reply-Path</code> bit was set on this message
	         * <P>Type: BOOLEAN</P>
	         */
	        public static final String REPLY_PATH_PRESENT = "reply_path_present";
	
	        /**
	         * The service center (SC) through which to send the message, if present
	         * <P>Type: TEXT</P>
	         */
	        public static final String SERVICE_CENTER = "service_center";
	
	        /**
	         * Has the message been locked?
	         * <P>Type: INTEGER (boolean)</P>
	         */
	        public static final String LOCKED = "locked";
	
	        /**
	         * Error code associated with sending or receiving this message
	         * <P>Type: INTEGER</P>
	         */
	        public static final String ERROR_CODE = "error_code";
	
	        /**
	         * Meta data used externally.
	         * <P>Type: TEXT</P>
	         */
	        public static final String META_DATA = "meta_data";
	    }
	}
}
