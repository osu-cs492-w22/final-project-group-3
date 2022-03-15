package com.example.banishthem

data class ChampionMastery(
    val championId: Long,
    val championLevel: Int,
    val championPoints: Int,
    val lastPlayTime: Long,
    val championPointsSinceLastLevel: Long,
    val championPointsUntilNextLevel: Long,
    val chestGranted: Boolean,
    val tokensEarned: Int,
    val summonerId: String
)
