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

    override fun updateHomeFeedTimestamp(newTimestamp: Long) {
        provider.put("home_caching_time", newTimestamp)
    }

    override fun getHomeFeedTimestamp(): Long {
        return provider.getLong("home_caching_time") ?: 0L
    }

}

interface LegoraStorageProviderImplementation {
    fun updateAccessToken(token: String)

    fun getAccessToken(): String

    fun isUserLoggedIn(): Boolean

    fun updateHomeFeedTimestamp(newTimestamp: Long)

    fun getHomeFeedTimestamp(): Long

}