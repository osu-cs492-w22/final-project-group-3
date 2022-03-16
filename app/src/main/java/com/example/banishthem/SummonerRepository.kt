package com.example.banishthem

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SummonerRepository(private val service: RiotService,
                         private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadRepositoriesSummonerSearch(name: String): Result<Summoner> = withContext(ioDispatcher) {
            try {
                val results = service.searchSummoners(name)
                Result.success(results)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

}