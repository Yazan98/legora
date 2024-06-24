package com.yazantarifi.legora.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazantarifi.legora.caching.dao.ChampionsDao
import com.yazantarifi.legora.caching.models.ChampionCacheEntity

@Database(entities = [ChampionCacheEntity::class], version = 1)
abstract class LegoraDatabase: RoomDatabase() {

    abstract fun getChampionsDao(): ChampionsDao

}
