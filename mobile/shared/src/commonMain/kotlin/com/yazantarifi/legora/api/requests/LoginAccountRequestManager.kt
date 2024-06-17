package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.models.LoginRequestBody
import com.yazantarifi.legora.response.AuthResponse
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class LoginAccountRequestManager constructor(
    private val httpClient: HttpClient
): LegoraRequestManager<LoginRequestBody, LegoraResponse<AuthResponse>>() {

    override fun getRequestInfo(
        requestBody: LoginRequestBody,
        onSuccess: (LegoraResponse<AuthResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<LoginRequestBody, LegoraResponse<AuthResponse>>(
                httpClient,
                requestBody,
                LegoraSharedStorage.requestsListener?.getRequestHeaders() ?: hashMapOf(),
                getFullRequestUrl("api/v1/accounts/login"),
                onSuccess,
                onError
            )
        }
    }

    override fun getRequestMethod(): LegoraRequestMethod {
        return LegoraRequestMethod.POST
    }

}
