package com.example.banishthem

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
        override fun onPreferenceTreeClick(preference: Preference): Boolean {
//            return preference.key == context?.getString(0)
            val appSettingsPref: SharedPreferences = this.requireActivity().getSharedPreferences("appSettingPrefs", Context.MODE_PRIVATE)
            val sharedPrefsEdit: SharedPreferences.Editor = appSettingsPref.edit()
            //isNightModeOn = appSettingsPref.getBoolean("@string/dlMode_settings", !isNightModeOn)

            isNightModeOn = appSettingsPref.getBoolean("@string/dlMode_settings", !isNightModeOn)
            isNightModeOn = !isNightModeOn
            if(isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("@string/dlMode_settings", true)
                sharedPrefsEdit.apply()
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("@string/dlMode_settings", false)
                sharedPrefsEdit.apply()
            }



            Log.d("onPreferenceTreeClick", isNightModeOn.toString())
            return preference.key == "@string/dlMode_settings"
        }
    }
}