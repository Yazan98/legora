package com.yazantarifi.legora.api


import com.yazantarifi.legora.LegoraSharedStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.UnknownHostException

actual class LegoraHttpClient {

    actual val httpClient: HttpClient = HttpClient {
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

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status.value in 400..500) {
                    LegoraSharedStorage.requestsListener?.onUnAutherizedUser()
                }
            }
        }
    }

}
