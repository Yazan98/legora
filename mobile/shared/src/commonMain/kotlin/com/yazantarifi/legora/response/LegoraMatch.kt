package com.yazantarifi.legora.response

import com.yazantarifi.legora.home.items.HomeScreenItem
import com.yazantarifi.legora.home.items.HomeScreenItemType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.abs

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
): HomeScreenItem {
    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.MATCH_HISTORY_LOL
    }

    fun getGoldValue(): String {
        return formatNumber(gold?.toLong() ?: 0L)
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

@Serializable
data class LegoraMatchChampion(
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
)