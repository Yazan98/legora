package com.yazantarifi.legora.context

class LegoraStorageProvider constructor(private val provider: LegoraStorageKeyValue): LegoraStorageProviderImplementation {
    override fun updateAccessToken(token: String) {
        provider.put("token", token)
    }

    override fun getAccessToken(): String {
        return provider.getString("token") ?: ""
    }

    override fun isUserLoggedIn(): Boolean {
        return getAccessToken().isNotEmpty()
    }

}

interface LegoraStorageProviderImplementation {
    fun updateAccessToken(token: String)

    fun getAccessToken(): String

    fun isUserLoggedIn(): Boolean
}