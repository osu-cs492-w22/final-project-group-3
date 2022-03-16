package com.example.banishthem

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import androidx.fragment.app.viewModels


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(R.layout.fragment_search) {
    // TODO: Rename and change types of parameters

    private val summonerViewModel: SummonerSearchViewModel by viewModels()
    private val championViewModel: ChampionSearchViewModel by viewModels()

    private lateinit var entry_text: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lightModeBtn = view.findViewById<MaterialButton>(R.id.btn_light_theme)
        val darkModeBtn = view.findViewById<MaterialButton>(R.id.btn_dark_theme)
        val searchBtn = view.findViewById<MaterialButton>(R.id.btn_search)

        val appSettingsPref: SharedPreferences = this.requireActivity().getSharedPreferences("appSettingPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingsPref.edit()
        val isNightModeOn: Boolean = appSettingsPref.getBoolean("NightMode", false)

        val shareBtn = view.findViewById<Button>(R.id.btn_share)

        entry_text = view.findViewById(R.id.et_search_box)

        championViewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            if (searchResults != null) {
                Log.i("Champion Search Observer", searchResults[0].championId.toString())
            }
        }

        summonerViewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            if (searchResults != null) {
                Log.i("Summoner Search Observer", searchResults.id)
                championViewModel.loadSearchResults(searchResults.id)
            }
        }

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        searchBtn.setOnClickListener {

            val query =  entry_text.text.toString()

            summonerViewModel.loadSearchResults(query)
            Log.i("Search Button", "Search Button Clicked")
        }

        lightModeBtn.setOnClickListener{
            if(isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()
            }
        }
        darkModeBtn.setOnClickListener{
            if(!isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }
        }

        shareBtn.setOnClickListener{
            val s = "TEST2"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s)
            //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT HERE...TEST")
            startActivity(Intent.createChooser(shareIntent, null))

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, entry_text)
                }
            }
    }

     */
}