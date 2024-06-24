package com.yazantarifi.legora.api

import com.yazantarifi.legora.LegoraSharedStorage
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class LegoraHttpClient {
    actual val httpClient: HttpClient = HttpClient(Darwin) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }

        defaultRequest {
            host = LegoraApiConstants.BASE_URL
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Type", "application/json")
                append("Accept", "application/json")
            }
        }

        expectSuccess = false
        developmentMode = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                isLenient = true
            })
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status.value in 400..500) {
                    LegoraSharedStorage.requestsListener?.onUnAutherizedUser()
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}