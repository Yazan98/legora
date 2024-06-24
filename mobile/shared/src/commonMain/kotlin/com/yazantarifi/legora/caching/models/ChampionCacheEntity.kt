package com.yazantarifi.legora.caching.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("champions")
data class ChampionCacheEntity(
    @PrimaryKey val name: String,
    @ColumnInfo("id") val id: Long,
    @ColumnInfo("icon") val icon: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("order") val order: Int,
    @ColumnInfo("isFreeToPlay") val isFreeToPlay: Boolean,
)
