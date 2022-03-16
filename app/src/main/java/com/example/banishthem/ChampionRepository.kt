package com.example.banishthem

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChampionRepository (private val service: ChampionService,
                          private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadRepositoriesChampionSearch(encryptedId: String): Result<List<ChampionMastery>> = withContext(ioDispatcher) {
        try {
            val results = service.searchChampions(encryptedId)
            Result.success(results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}