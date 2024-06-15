package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraAccount(
    @SerialName("summonerName") val summonerName: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("summonerRegion") val summonerRegion: String? = "",
    @SerialName("summonerServerCode") val summonerServerCode: String? = "",
    @SerialName("email") val email: String? = "",
    @SerialName("createdAt") val createdAt: String? = "",
    @SerialName("id") val id: Long? = 0L,
)