package com.yazantarifi.legora.champions.widgets

import com.yazantarifi.legora.champions.ChampionItem
import com.yazantarifi.legora.champions.ChampionItemType

data class ChampionTitleWidget(
    val title: String
): ChampionItem {
    override fun getType(): ChampionItemType {
        return ChampionItemType.TITLE
    }
}