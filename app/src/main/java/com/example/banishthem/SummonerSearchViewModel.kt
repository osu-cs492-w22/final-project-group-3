package com.example.banishthem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SummonerSearchViewModel : ViewModel() {
    private val repository = SummonerRepository(RiotService.create())

    private val _searchResults = MutableLiveData<Summoner?>(null)
    val searchResults: LiveData<Summoner?> = _searchResults


    fun loadSearchResults(query: String) {
        viewModelScope.launch {
            val result = repository.loadRepositoriesSummonerSearch(query)
            _searchResults.value = result.getOrNull()
        }
    }
}