package com.yazantarifi.legora.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterAccountRequestBody(
    @SerialName("email") val email: String? = "",
    @SerialName("password") val password: String? = "",
    @SerialName("summonerName") val summonerName: String? = "",
    @SerialName("region") val region: String? = "",
    @SerialName("serverCode") val serverCode: String? = "",
)