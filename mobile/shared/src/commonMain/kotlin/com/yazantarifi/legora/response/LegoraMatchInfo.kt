package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraMatchInfo(
    @SerialName("id") val id: String? = "",
    @SerialName("creationTimestamp") val creationTimestamp: String? = "",
    @SerialName("mode") val mode: String? = "",
    @SerialName("isVictory") val isVictory: Boolean? = false,
    @SerialName("champion") val champion: LegoraMatchInfoChampion? = null,
    @SerialName("players") val players: List<LegoraMatchPlayer>? = null,
)

@Serializable
data class LegoraMatchPlayer(
    @SerialName("items") val items: List<String>? = null,
    @SerialName("champion") val champion: LegoraMatchInfoChampion? = null,
    @SerialName("kills") val kills: Int? = 0,
    @SerialName("assists") val assists: Int? = 0,
    @SerialName("deaths") val deaths: Int? = 0,
    @SerialName("farm") val farm: Int? = 0,
    @SerialName("gold") val gold: Int? = 0,
)

@Serializable
data class LegoraMatchInfoChampion(
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
)
