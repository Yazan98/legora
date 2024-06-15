package com.yazantarifi.legora

import com.yazantarifi.legora.api.LegoraApiConstants
import com.yazantarifi.legora.api.LegoraRequestListener
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object LegoraSharedStorage {

    var requestsListener: LegoraRequestListener? = null

    fun getApplicationHeaders(
        appVersion: String,
        token: String
    ): HashMap<String, String> {
        return HashMap<String, String>().apply {
            put(LegoraApiConstants.LANGUAGE, "en")
            put(LegoraApiConstants.PLATFORM, getPlatform().name)
            if (appVersion.isNotEmpty()) {
                put(LegoraApiConstants.APP_VERSION, appVersion)
            }

            if (token.isNotEmpty()) {
                put(LegoraApiConstants.AUTH, token)
            }
        }
    }

}
