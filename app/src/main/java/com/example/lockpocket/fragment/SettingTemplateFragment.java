package com.example.lockpocket.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.example.lockpocket.R;

public class SettingTemplateFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_template, null);
    }
}
