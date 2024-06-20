package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.LegoraChampion
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetTacticesChampionsListRequestManager constructor(
    private val httpClient: HttpClient
): LegoraRequestManager<Unit, LegoraResponse<ArrayList<LegoraChampion>>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<ArrayList<LegoraChampion>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraChampion>>>(
                httpClient,
                requestBody,
                LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
                getFullRequestUrl("api/v1/champions/tft"),
                onSuccess,
                onError
            )
        }
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }
}