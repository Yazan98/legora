package com.yazantarifi.legora.api

import io.ktor.client.HttpClient

expect class LegoraHttpClient {
    val httpClient: HttpClient
}