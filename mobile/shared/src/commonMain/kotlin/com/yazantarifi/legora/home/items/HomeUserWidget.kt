package com.yazantarifi.legora.home.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("HomeUserWidget")
data class HomeUserWidget(
    val name: String,
    val server: String,
    val image: String
): HomeScreenItem {
    override fun getType(): HomeScreenItemType {
        return HomeScreenItemType.USER_WIDGET
    }
}