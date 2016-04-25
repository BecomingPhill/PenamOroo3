package com.thetechguys.penamoroo1p3.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.thetechguys.penamoroo1p3.R;

/**
 * Created by Evan Anger on 8/13/14.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
