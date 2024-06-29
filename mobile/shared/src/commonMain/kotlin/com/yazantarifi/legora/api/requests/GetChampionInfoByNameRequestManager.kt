package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.ChampionInfo
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetChampionInfoByNameRequestManager constructor(
    private val httpClient: HttpClient,
    private val requestHeaders: HashMap<String, String>
): LegoraRequestManager<String, LegoraResponse<ChampionInfo>>() {

    override fun getRequestInfo(
        requestBody: String,
        onSuccess: (LegoraResponse<ChampionInfo>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<ChampionInfo>>(
                httpClient,
                Unit,
                requestHeaders,
                getFullRequestUrl("api/v1/champions/lol/${requestBody}"),
                onSuccess,
                onError
            )
        }
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
