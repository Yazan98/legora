package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetLeagueMatchHistoryRequestManager constructor(
    private val httpClient: HttpClient
): LegoraRequestManager<Unit, LegoraResponse<ArrayList<LegoraMatch>>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<ArrayList<LegoraMatch>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraMatch>>>(
                httpClient,
                requestBody,
                LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
                getFullRequestUrl("api/v1/matches/lol"),
                onSuccess,
                onError
            )
        }
    }

    override suspend fun getSuspendedRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<ArrayList<LegoraMatch>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onExecuteRequest<Unit, LegoraResponse<ArrayList<LegoraMatch>>>(
            httpClient,
            requestBody,
            LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
            getFullRequestUrl("api/v1/matches/lol"),
            onSuccess,
            onError
        )
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
