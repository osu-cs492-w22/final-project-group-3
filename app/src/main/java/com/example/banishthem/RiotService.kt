package com.example.banishthem

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//API Key
private const val API_KEY = "RGAPI-0c0ab32a-7a1d-48d7-ade4-0697174c7067"

interface RiotService {
    @GET("/lol/summoner/v4/summoners/by-name/{name}?api_key=${API_KEY}")
    suspend fun searchSummoners(@Path("name") summonerName: String) : Summoner

    companion object {
        private const val BASE_URL = "https://na1.api.riotgames.com"
        fun create() : RiotService {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(RiotService::class.java)
        }
    }
}
