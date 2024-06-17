package com.yazantarifi.legora.home.items

import com.yazantarifi.legora.home.HomeWidgetItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("HomeNewsItemWidget")
data class HomeNewsItemWidget(
    val item: HomeWidgetItem
): HomeScreenItem {
    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.LATEST_NEWS_WIDGET
    }
}
