package fr.jaetan.botiot.networking

import fr.jaetan.botiot.models.Difficulties
import fr.jaetan.botiot.models.Game
import fr.jaetan.botiot.networking.models.CreateGameRequest
import fr.jaetan.botiot.networking.models.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json

object GameApi {
    private val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.ANDROID
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            header("Content-Type", "application/json")
            url("http://192.168.1.184:8080")
        }
    }

    suspend fun createGame(difficulty: Difficulties): Game {
        return client.post("games/create") {
            setBody(CreateGameRequest(difficulty.id))
        }
        .body<GameResponse>()
        .game
    }

    suspend fun startGame(gameId: Int) {
        client.post("games/start/$gameId")
    }
}