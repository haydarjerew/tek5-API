package com.example.models

data class PlayerStats(
    val username: String?,
    val skills: List<SkillInfo>
)

data class SkillInfo(
    val skill: Int,
    val xp: Double,
    val lvl: Int,
    val lastLvl: Int
)