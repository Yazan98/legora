package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("account") val account: LegoraAccount? =  null,
    @SerialName("auth") val auth: AuthInfo? = null
)

@Serializable
data class AuthInfo(
    @SerialName("accessToken") val accessToken: String? = "",
    @SerialName("refreshToken") val refreshToken: String? = "",
)