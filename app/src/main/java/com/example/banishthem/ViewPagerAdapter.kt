package com.example.banishthem


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager, lifecycle) {
    //private val fragment_list: MutableList<Fragment> = ArrayList()
    //private val title_list: MutableList<String> = ArrayList()

    override fun getItemCount(): Int {
        //return fragment_list.size
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return SearchFragment()
    }
    /*
    fun addFragment(fragment: Fragment, title: String){
        fragment_list.add(fragment)
        title_list.add(title)
    }
    */

}