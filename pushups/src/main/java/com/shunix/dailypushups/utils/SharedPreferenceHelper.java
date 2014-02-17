package com.shunix.dailypushups.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * 
 * @author Ray WANG
 * @since Nov 22nd, 2013
 * @version 1.0.0
 */
public class SharedPreferenceHelper {
	private static SharedPreferenceHelper helper;
	private SharedPreferences sharedPreferences;
	private Editor editor;

	public static SharedPreferenceHelper getInstance(Context context) {
		if (helper == null) {
			helper = new SharedPreferenceHelper(context);
		}
		return helper;
	}

	public SharedPreferenceHelper(Context context) {
		this.sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public void saveData(String key, String value) {
		if (editor == null) {
			editor = this.sharedPreferences.edit();
		}
		editor.putString(key, value);
	}

	public void removeData(String key) {
		if (editor == null) {
			editor = this.sharedPreferences.edit();
		}
		editor.remove(key);
	}

	public String getValue(String key) {
		return sharedPreferences.getString(key, "");
	}
}
