package com.yazantarifi.legora.api.base

import com.yazantarifi.legora.api.LegoraApiConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class LegoraRequestManager<RequestBody, Response>: CoroutineScope {

    private val parentJob = SupervisorJob()
    private val backgroundDispatcher = Dispatchers.IO

    override val coroutineContext: CoroutineContext
        get() = parentJob + backgroundDispatcher

    suspend inline fun <reified RequestBody, reified Response> onExecuteRequest(
        httpClient: HttpClient,
        requestBody: RequestBody,
        headers: HashMap<String, String>,
        requestUrl: String,
        onSuccess: (Response) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        httpClient.also {
            try {
                val request = when (getRequestMethod()) {
                    LegoraRequestMethod.POST -> it.post {
                        url(requestUrl)
                        headers.forEach {
                            header(it.key, it.value)
                        }

                        setBody(requestBody)
                    }

                    LegoraRequestMethod.GET -> it.get {
                        url(requestUrl)
                        headers.forEach {
                            header(it.key, it.value)
                        }
                    }
                }

                if (isSuccess(request.status.value)) {
                    val response = request.body<Response>()
                    onSuccess(response)
                } else {
                    onError(RuntimeException(request.bodyAsText()))
                }
            } catch (ex: Exception) {
                onError(ex)
            }
        }
    }

    fun isSuccess(statusCode: Int): Boolean {
        return statusCode in 200..299
    }

    protected fun getFullRequestUrl(path: String): String {
        return LegoraApiConstants.BASE_URL + path
    }

    open suspend fun getSuspendedRequestInfo(
        requestBody: RequestBody,
        onSuccess: (Response) -> Unit,
        onError: (Throwable) -> Unit,
    ) = Unit

    abstract fun getRequestInfo(
        requestBody: RequestBody,
        onSuccess: (Response) -> Unit,
        onError: (Throwable) -> Unit,
    )

    abstract fun getRequestMethod(): LegoraRequestMethod


}