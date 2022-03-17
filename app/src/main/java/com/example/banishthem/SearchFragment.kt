package com.example.banishthem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton


/**
 * A simple Fragment subclass.
 * Use the SearchFragment.newInstance factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val summonerViewModel: SummonerSearchViewModel by viewModels()
    private val championViewModel: ChampionSearchViewModel by viewModels()
    private val masteryListAdapter = ChampionMasteryAdapter()

    private lateinit var entry_text: EditText
    private lateinit var masteryListRV: RecyclerView
    private lateinit var searchErrorTV: TextView

    private var searchPressed: Boolean = false
    private val championMasteries: MutableList<ChampionMastery> = mutableListOf()
    private var query: String =""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lightModeBtn = view.findViewById<MaterialButton>(R.id.btn_light_theme)
        val darkModeBtn = view.findViewById<MaterialButton>(R.id.btn_dark_theme)
        val searchBtn = view.findViewById<MaterialButton>(R.id.btn_search)
        val shareBtn = view.findViewById<Button>(R.id.btn_share)
        searchErrorTV = view.findViewById(R.id.tv_search_error)

        val appSettingsPref: SharedPreferences = this.requireActivity().getSharedPreferences("appSettingPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingsPref.edit()
        val isNightModeOn: Boolean = appSettingsPref.getBoolean("NightMode", false)


        masteryListRV = view.findViewById(R.id.rv_mastery_list)
        masteryListRV.layoutManager = LinearLayoutManager(requireContext())
        masteryListRV.setHasFixedSize(true)
        masteryListRV.adapter = masteryListAdapter

        entry_text = view.findViewById(R.id.et_search_box)

        championViewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            if (searchResults != null) {
                Log.i("Champion Search Observer", searchResults[0].championId.toString())

                val championListSize = searchResults.size

                if(championListSize < 5){
                    for(i in 0 until championListSize){
                        championMasteries.add(i, searchResults[i])
                    }
                }
                else{
                    for(i in 0..4){
                        championMasteries.add(i, searchResults[i])
                    }
                }
                masteryListAdapter.updateChampionMastery(championMasteries)
                //masteryListAdapter.addChampionMastery(searchResults[0],0)
               //masteryListAdapter.addChampionMastery(searchResults[1], 1)
                shareBtn.visibility = View.VISIBLE
            }
            else{
                shareBtn.visibility = View.INVISIBLE
            }
        }

        summonerViewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            if (searchResults != null) {
                Log.i("Summoner Search Observer", searchResults.id)
                championViewModel.loadSearchResults(searchResults.id)
                searchErrorTV.visibility = View.INVISIBLE
                searchPressed = false
            } else {
                if(searchPressed) {
                    //display error
                    Log.i("Summoner Search Observer", "summoner id does not exist")
                    searchErrorTV.visibility = View.VISIBLE
                    val emptyChampionMasteries: MutableList<ChampionMastery> = mutableListOf()
                    masteryListAdapter.updateChampionMastery(emptyChampionMasteries)
                }
            }
        }

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        searchBtn.setOnClickListener {

            query =  entry_text.text.toString()

            summonerViewModel.loadSearchResults(query)
            Log.i("Search Button", "Search Button Clicked")
            searchPressed = true
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
            var s: String = "Summoner: $query \n "
            for(i in 0..4) {
                s += masteryListAdapter.map[championMasteries[i].championId.toString()] + ": " + championMasteries[i].championPoints + " points"
                if (i != 4)
                    s+= "\n"
            }
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