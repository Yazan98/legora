package com.yazantarifi.legora.response

import com.yazantarifi.legora.champions.ChampionItem
import com.yazantarifi.legora.champions.ChampionItemType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegoraChampion(
    @SerialName("id") val id: Long? = 0L,
    @SerialName("name") val name: String? = "",
    @SerialName("icon") var icon: String? = "",
    @SerialName("isFreeToPlay") val isFreeToPlay: Boolean? = false,
    @SerialName("type") val type: String? = "",
): ChampionItem {
    override fun getType(): ChampionItemType {
        return ChampionItemType.CHAMPION
    }

}