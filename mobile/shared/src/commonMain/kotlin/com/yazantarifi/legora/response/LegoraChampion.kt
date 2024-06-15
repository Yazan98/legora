package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraChampion(
    @SerialName("id") val id: Long? = 0L,
    @SerialName("name") val name: String? = "",
    @SerialName("icon") val icon: String? = "",
    @SerialName("isFreeToPlay") val isFreeToPlay: Boolean? = false,
    @SerialName("type") val type: String? = "",
)