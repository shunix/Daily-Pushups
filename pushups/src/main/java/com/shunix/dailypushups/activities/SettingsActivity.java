package com.shunix.dailypushups.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.shunix.dailypushups.R;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 16th, 2014
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
