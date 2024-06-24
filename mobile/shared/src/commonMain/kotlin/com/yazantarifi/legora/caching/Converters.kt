package com.yazantarifi.legora.caching

import androidx.room.TypeConverter
import com.yazantarifi.legora.caching.models.ChampionCacheEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromChampionInstance(championInstance: ChampionCacheEntity): String {
        return Json.encodeToString(championInstance)
    }

    @TypeConverter
    fun toChampionInstance(championInstance: String): ChampionCacheEntity {
        return Json.decodeFromString(championInstance)
    }

}