package com.yazantarifi.legora.home.items

import com.yazantarifi.legora.home.HomeWidgetItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("HomePlayersWidget")
data class HomePlayersWidget(
    val item: List<HomeWidgetItem>
): HomeScreenItem {
    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.PLAYERS_WIDGET
    }
}