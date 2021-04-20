package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.project.ui.main.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.util.Log;
import com.google.firebase.auth.FirebaseUser;


public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        mAuth = FirebaseAuth.getInstance();


        getSupportActionBar().setTitle("Settings");


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        if (findViewById(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getFragmentManager().beginTransaction().add(R.id.idFrameLayout, new RealSetting()).commit();
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference logOut = findPreference(getString(R.string.logoutButton));
            logOut.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    FirebaseAuth.getInstance().signOut();
                    return true;
                }
            });
            
            CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference(getString(R.string.night));
            //    CheckBoxPreference nightMode = findPreference(getString(R.string.night));
            checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean yes = (boolean) newValue;
                    if (yes) {
                        getListView().setBackgroundColor(Color.parseColor("#222222"));
                    }
                    else {
                        getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    return true;
                }
            });
        }
    }

}
