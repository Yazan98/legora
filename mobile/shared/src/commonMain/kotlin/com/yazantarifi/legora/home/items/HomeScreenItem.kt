package com.yazantarifi.legora.home.items

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeScreenItem {
    fun getType(): HomeScreenItemType
}

enum class HomeScreenItemType {
    USER_WIDGET,
    TITLE_WIDGET,
    LATEST_NEWS_WIDGET,
    PLAYERS_WIDGET
}
