package com.example.banishthem

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

val summonersArray = arrayOf(
    "Summoner1",
    "Summoner2",
    "Summoner3",
    "Summoner4",
    "Summoner5"
)

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



    }
}