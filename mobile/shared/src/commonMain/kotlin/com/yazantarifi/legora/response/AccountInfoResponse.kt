package com.yazantarifi.legora.response

import com.yazantarifi.legora.account.AccountItem
import com.yazantarifi.legora.account.AccountItemType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.abs

@Serializable
data class AccountInfoResponse(
    @SerialName("user") val user: LegoraAccount? = null,
    @SerialName("summonerInfo") val summonerInfo: AccountPropsResponse? = null
): AccountItem {
    override fun getType(): AccountItemType {
        return AccountItemType.HEADER
    }

}

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
) {

    fun getPointsValue(): String {
        return formatNumber(points?.toLong() ?: 0L)
    }

    private fun formatNumber(value: Long): String {
        val absValue = abs(value) // Consider absolute value for determining the format

        return when {
            absValue >= 1_000_000 -> {
                // Format for millions
                val millions = value / 1_000_000.0
                "${(millions * 10).toInt() / 10.0}m"
            }
            absValue >= 1_000 -> {
                // Format for thousands
                val thousands = value / 1_000.0
                "${(thousands * 10).toInt() / 10.0}k"
            }
            else -> value.toString() // Return the number as it is for values less than 1000
        }
    }

}