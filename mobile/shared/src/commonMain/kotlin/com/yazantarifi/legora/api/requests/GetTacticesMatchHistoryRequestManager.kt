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
    private val httpClient: HttpClient
): LegoraRequestManager<Unit, LegoraResponse<ArrayList<LegoraTftMatch>>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        url: String,
        onSuccess: (LegoraResponse<ArrayList<LegoraTftMatch>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraTftMatch>>>(
                httpClient,
                requestBody,
                LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
                getFullRequestUrl(url),
                onSuccess,
                onError
            )
        }
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
