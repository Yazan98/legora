package com.yazantarifi.legora.response

import com.yazantarifi.legora.account.AccountItem
import com.yazantarifi.legora.account.AccountItemType
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
): HomeScreenItem, AccountItem {

    fun getSortedUnitList(): List<LegoraTftUnit> {
        return (units?.sortedBy { it.items?.isNotEmpty() == true } ?: arrayListOf()).asReversed()
    }

    override fun getHomeWidgetType(): HomeScreenItemType {
        return HomeScreenItemType.MATCH_HISTORY_TFT
    }

    override fun getType(): AccountItemType {
        return AccountItemType.TFT_MATCH_HISTORY
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
