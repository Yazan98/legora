package com.yazantarifi.legora.home.items

import kotlinx.serialization.Serializable

interface HomeScreenItem {
    fun getType(): HomeScreenItemType
}

enum class HomeScreenItemType {
    USER_WIDGET,
    TITLE_WIDGET,
    LATEST_NEWS_WIDGET,
    PLAYERS_WIDGET,
    MATCH_HISTORY_LOL,
    MATCH_HISTORY_TFT,
}
