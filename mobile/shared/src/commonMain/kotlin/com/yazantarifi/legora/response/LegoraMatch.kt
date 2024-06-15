package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraMatch(
    @SerialName("id") val id: String? = "",
    @SerialName("creationTimestamp") val creationTimestamp: String? = "",
    @SerialName("mode") val mode: String? = "",
    @SerialName("kills") val kills: Int? = 0,
    @SerialName("deaths") val deaths: Int? = 0,
    @SerialName("assists") val assists: Int? = 0,
    @SerialName("farm") val farm: Int? = 0,
    @SerialName("gold") val gold: Int? = 0,
    @SerialName("isVictory") val isVictory: Boolean? = false,
    @SerialName("items") val items: List<String>? = null,
    @SerialName("champion") val champion: LegoraMatchChampion? = null,
)

@Serializable
data class LegoraMatchChampion(
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
)