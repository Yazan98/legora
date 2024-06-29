package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraResponse
import com.yazantarifi.legora.response.LegoraTftMatch
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetTacticesMatchHistoryRequestManager constructor(
    private val httpClient: HttpClient,
    private val requestHeaders: HashMap<String, String>
): LegoraRequestManager<Unit, LegoraResponse<ArrayList<LegoraTftMatch>>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<ArrayList<LegoraTftMatch>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraTftMatch>>>(
                httpClient,
                requestBody,
                requestHeaders,
                getFullRequestUrl("api/v1/matches/tft"),
                onSuccess,
                onError
            )
        }
    }

    override suspend fun getSuspendedRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<ArrayList<LegoraTftMatch>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraTftMatch>>>(
            httpClient,
            requestBody,
            requestHeaders,
            getFullRequestUrl("api/v1/matches/tft"),
            onSuccess,
            onError
        )
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
