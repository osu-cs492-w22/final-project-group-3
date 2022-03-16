package com.example.banishthem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChampionSearchViewModel  : ViewModel() {
    private val repository = ChampionRepository(ChampionService.create())

    private val _searchResults = MutableLiveData<List<ChampionMastery>?>(null)
    val searchResults: LiveData<List<ChampionMastery>?> = _searchResults


    fun loadSearchResults(encryptedId: String) {
        viewModelScope.launch {
            val result = repository.loadRepositoriesChampionSearch(encryptedId)
            _searchResults.value = result.getOrNull()
        }
    }
}