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
): LegoraRequestManager<String, LegoraResponse<LegoraMatchInfo>>() {

    override fun getRequestInfo(
        requestBody: String,
        onSuccess: (LegoraResponse<LegoraMatchInfo>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<LegoraMatchInfo>>(
                httpClient,
                Unit,
                LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
                getFullRequestUrl("api/v1/matches/lol/info/${requestBody}"),
                onSuccess,
                onError
            )
        }
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
