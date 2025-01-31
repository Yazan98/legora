package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.response.AccountInfoResponse
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class GetProfileInfoRequestManager constructor(
    private val httpClient: HttpClient,
    private val requestHeaders: HashMap<String, String>
): LegoraRequestManager<Unit, LegoraResponse<AccountInfoResponse>>() {

    override fun getRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<AccountInfoResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<Unit, LegoraResponse<AccountInfoResponse>>(
                httpClient,
                requestBody,
                requestHeaders,
                getFullRequestUrl("api/v1/accounts/info"),
                onSuccess,
                onError
            )
        }
    }

    override suspend fun getSuspendedRequestInfo(
        requestBody: Unit,
        onSuccess: (LegoraResponse<AccountInfoResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onExecuteRequest<Unit, LegoraResponse<AccountInfoResponse>>(
            httpClient,
            requestBody,
            requestHeaders,
            getFullRequestUrl("api/v1/accounts/info"),
            onSuccess,
            onError
        )
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.GET
    }

}
