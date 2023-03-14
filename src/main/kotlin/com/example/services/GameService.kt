package com.example.services

import com.example.models.PlayerStats
import com.example.models.SkillInfo
import com.example.plugins.configureRouting
import java.io.File
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
object GameService {
    fun getPlayerStats(username: String?): PlayerStats? {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass)
        val file = File("C:/Users/selfi/tek5/tek5-game/data/saves/$username")
        logger.info("Attempting to locate JSON file for ${username} at $file")
        return if (file.exists()) {
            logger.info("Reading JSON data for ${username}")
            val jsonString = file.readText()
            val rootObject = JSONObject(jsonString)
            val skillsArray = rootObject.getJSONArray("skills")
            val skillsList = mutableListOf<SkillInfo>()
            for (i in 0 until skillsArray.length()) {
                val skillObject = skillsArray.getJSONObject(i)
                val skill = skillObject.getInt("skill")
                val xp = skillObject.getDouble("xp")
                val lvl = skillObject.getInt("lvl")
                val lastLvl = skillObject.getInt("lastLvl")
                val skillInfo = SkillInfo(skill, xp, lvl, lastLvl)
                skillsList.add(skillInfo) // add the skillInfo object to the skillsList
            }
            //logger.info("getPlayerStats returning the following: ${skillsList}")
            PlayerStats(username, skillsList.toList())
        } else {
            logger.info("Failed to find JSON data for ${username}")
            null
        }
    }
}