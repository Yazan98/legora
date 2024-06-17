package com.yazantarifi.legora.caching.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeScreenWidgetEntity(
    @PrimaryKey val widgetIndex: Int,
    @ColumnInfo(name = "widget_name") val name: String?,
    @ColumnInfo(name = "widget_payload") val payload: String?
)