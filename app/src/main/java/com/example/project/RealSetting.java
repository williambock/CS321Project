package com.example.project;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;


public class RealSetting extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // add preference fragment from xml folder.
        addPreferencesFromResource(R.xml.root_preferences);
    }
}
