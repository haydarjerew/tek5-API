package com.example.plugins

import com.example.services.GameService
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
fun Application.configureRouting() {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    routing {
        get("/") {
            call.respondText("Welcome to the 2011Scape Server API - please see the github page for documentation!")
        }
        get("/getPlayerStats/{playerName}") {
            val playerName = call.parameters["playerName"]
            logger.info("Getting player stats for ${playerName}")
            val playerStats = GameService.getPlayerStats(playerName)
            if (playerStats != null) {
                //logger.info("Found the following stats for ${playerName}: ${playerStats.skills}")
                call.respond(playerStats.skills.toString())
            } else {
                logger.info("Player stats not found for ${playerName}")
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
