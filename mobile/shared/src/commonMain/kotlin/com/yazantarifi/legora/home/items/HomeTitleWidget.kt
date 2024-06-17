package com.yazantarifi.legora.home.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("HomeTitleWidget")
data class HomeTitleWidget(
    val title: String
): HomeScreenItem {
    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.TITLE_WIDGET
    }
}