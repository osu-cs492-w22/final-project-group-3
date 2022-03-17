package com.example.banishthem

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import android.util.Log
import androidx.preference.PreferenceManager

val summonersArray = arrayOf(
    "Summoner1",
    "Summoner2",
    "Summoner3",
    "Summoner4",
    "Summoner5"
)

var isNightModeOn: Boolean = false;


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var view_pager = findViewById<ViewPager2>(R.id.viewpager)
        var adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = adapter
        view_pager.setOffscreenPageLimit(4)

        var tab_layout = findViewById<TabLayout>(R.id.tablayout)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = summonersArray[position]
        }.attach()

        mySettings()

    }

    private fun mySettings() {
        val sharedPreference = getSharedPreferences("appSettingPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = sharedPreference.edit()
        isNightModeOn = sharedPreference.getBoolean("@string/dlMode_settings", true)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPrefsEdit.putBoolean("@string/dlMode_settings", true)
            sharedPrefsEdit.apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefsEdit.putBoolean("@string/dlMode_settings", false)
            sharedPrefsEdit.apply()
        }

    }
//    override fun onResume() {
//        super.onResume()
//        val appSettingsPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//        val sharedPrefsEdit: SharedPreferences.Editor = appSettingsPref.edit()
//        isNightModeOn = appSettingsPref.getBoolean("@string/dlMode_settings", !isNightModeOn)
//    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }



}