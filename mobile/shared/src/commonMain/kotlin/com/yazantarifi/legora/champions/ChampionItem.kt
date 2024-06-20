package com.yazantarifi.legora.champions

interface ChampionItem {

    fun getType(): ChampionItemType

}

enum class ChampionItemType {
    TITLE,
    CHAMPION,
    FREE_TO_PLAY
}
