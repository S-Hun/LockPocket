package com.example.lockpocket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.lockpocket.EditActivity;
import com.example.lockpocket.R;

public class SettingTemplateFragment extends PreferenceFragmentCompat {

    Preference editButton;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_template, null);
        editButton = findPreference("key_edit");

        editButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}
