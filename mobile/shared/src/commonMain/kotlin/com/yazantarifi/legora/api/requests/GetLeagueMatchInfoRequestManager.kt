package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraMatchInfo
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetLeagueMatchInfoRequestManager constructor(
    private val httpClient: HttpClient
): LegoraRequestManager<Unit, LegoraResponse<LegoraMatchInfo>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        url: String,
        onSuccess: (LegoraResponse<LegoraMatchInfo>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<LegoraMatchInfo>>(
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
