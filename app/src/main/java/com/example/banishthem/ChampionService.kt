package com.example.banishthem

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//API Key
private const val API_KEY = "RGAPI-00b63b1d-1779-46ef-b16e-9b2357d2a0dd"

interface ChampionService {
    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedId}?api_key=${API_KEY}")

    suspend fun searchChampions(@Path("encryptedId") encryptedSummonerId: String) : List<ChampionMastery>

    companion object {
        private const val BASE_URL = "https://na1.api.riotgames.com"
        fun create() : ChampionService {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(ChampionService::class.java)
        }
    }
}
