package com.example.lockpocket.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.lockpocket.EditActivity;
import com.example.lockpocket.R;
import com.example.lockpocket.utils.LockScreen;

public class SettingTemplateFragment extends PreferenceFragmentCompat {

    Preference editButton;
    SwitchPreference lockToggle;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_template, null);

        editButton = findPreference("key_edit");
        lockToggle = findPreference("key_lock_toggle");

        LockScreen.getInstance().init(getContext(),true);

        if (LockScreen.getInstance().isActive()) {
            lockToggle.setChecked(true);
        } else {
            lockToggle.setChecked(false);
        }

        editButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                startActivity(intent);
                return true;
            }
        });

        lockToggle.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (lockToggle.isChecked()) {
                    LockScreen.getInstance().deactivate();
                    lockToggle.setChecked(false);
                } else {
                    LockScreen.getInstance().active();
                    lockToggle.setChecked(true);
                }
                return false;
            }
        });
    }
}
