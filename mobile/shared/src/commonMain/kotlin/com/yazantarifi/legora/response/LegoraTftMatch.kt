package com.yazantarifi.legora.response

import com.yazantarifi.legora.home.items.HomeScreenItem
import com.yazantarifi.legora.home.items.HomeScreenItemType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraTftMatch(
    @SerialName("id") val id: String? = "",
    @SerialName("date") val date: String? = "",
    @SerialName("placement") val placement: Int? = 0,
    @SerialName("units") val units: List<LegoraTftUnit>? = null,
    @SerialName("augments") val augments: List<LegoraTftAugment>? = null,
): HomeScreenItem {

    fun getSortedUnitList(): List<LegoraTftUnit> {
        return (units?.sortedBy { it.items?.isNotEmpty() == true } ?: arrayListOf()).asReversed()
    }

    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.MATCH_HISTORY_TFT
    }

}

@Serializable
data class LegoraTftAugment(
    @SerialName("name") val name: String? = "",
    @SerialName("image") val image: String? = "",
)

@Serializable
data class LegoraTftUnit(
    @SerialName("image") val image: String? = "",
    @SerialName("items") val items: ArrayList<String>? = null,
)
