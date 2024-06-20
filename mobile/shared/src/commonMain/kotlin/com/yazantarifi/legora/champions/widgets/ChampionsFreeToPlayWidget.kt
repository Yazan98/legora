package com.yazantarifi.legora.champions.widgets

import com.yazantarifi.legora.champions.ChampionItem
import com.yazantarifi.legora.champions.ChampionItemType
import com.yazantarifi.legora.response.LegoraChampion

data class ChampionsFreeToPlayWidget(
    val champions: List<LegoraChampion>
): ChampionItem {
    override fun getType(): ChampionItemType {
        return ChampionItemType.FREE_TO_PLAY
    }
}
