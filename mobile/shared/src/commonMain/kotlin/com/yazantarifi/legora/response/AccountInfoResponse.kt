package com.yazantarifi.legora.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountInfoResponse(
    @SerialName("user") val user: LegoraAccount? = null,
    @SerialName("summonerInfo") val summonerInfo: AccountPropsResponse? = null
)

@Serializable
data class AccountPropsResponse(
    @SerialName("level") val level: Int? = 0,
    @SerialName("masteryPoints") val masteryPoints: Int? = 0,
    @SerialName("coverImage") val coverImage: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("accountId") val accountId: String? = "",
    @SerialName("accountHash") val accountHash: String? = "",
    @SerialName("summonerHighlightName") val summonerHighlightName: String? = "",
    @SerialName("serverHighlightName") val serverHighlightName: String? = "",
    @SerialName("profileImage") val profileImage: String? = "",
    @SerialName("isLolMatchesFound") val isLolMatchesFound: Boolean? = false,
    @SerialName("isTftMatchesFound") val isTftMatchesFound: Boolean? = false,
    @SerialName("topChampionsMastery") val topChampionsMastery: ArrayList<AccountMasteryChampion>? = null,
    @SerialName("widgets") val widgets: ArrayList<AccountWidget>? = null,

)

@Serializable
data class AccountWidget(
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
    @SerialName("link") val link: String? = "",
)

@Serializable
data class AccountMasteryChampion(
    @SerialName("icon") val icon: String? = "",
    @SerialName("name") val name: String? = "",
    @SerialName("id") val id: Long? = 0,
    @SerialName("level") val level: Int? = 0,
    @SerialName("points") val points: Int? = 0,
)