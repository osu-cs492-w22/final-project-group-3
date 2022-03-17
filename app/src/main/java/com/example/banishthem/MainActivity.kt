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

        var tab_layout = findViewById<TabLayout>(R.id.tablayout)
        TabLayoutMediator(tab_layout, view_pager) {tab, position ->
            tab.text = summonersArray[position]
        }.attach()

        //mySettings()
    }

//    private fun mySettings(){
//        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        val switch = prefs.getBoolean("@string/dlMode_settings", true)
//
//        if(switch){
//            Log.i("switch is on", switch.toString())
//        }
//        else{
//            Log.i("switch is off", switch.toString())
//        }
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