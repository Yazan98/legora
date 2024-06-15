package com.yazantarifi.legora.api.requests

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.base.LegoraRequestManager
import com.yazantarifi.legora.api.base.LegoraRequestMethod
import com.yazantarifi.legora.models.RegisterAccountRequestBody
import com.yazantarifi.legora.response.AuthResponse
import com.yazantarifi.legora.response.LegoraResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch

class RegisterAccountRequestManager constructor(
    private val httpClient: HttpClient
): LegoraRequestManager<RegisterAccountRequestBody, LegoraResponse<AuthResponse>>() {

    override fun getRequestInfo(
        requestBody: RegisterAccountRequestBody,
        url: String,
        onSuccess: (LegoraResponse<AuthResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        launch {
            onExecuteRequest<RegisterAccountRequestBody, LegoraResponse<AuthResponse>>(
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
        return LegoraRequestMethod.POST
    }

}
