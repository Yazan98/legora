package com.yazantarifi.legora.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazantarifi.legora.caching.dao.HomeScreenDao
import com.yazantarifi.legora.caching.models.HomeScreenWidgetEntity

@Database(entities = [HomeScreenWidgetEntity::class], version = 1)
abstract class LegoraDatabase: RoomDatabase() {

    abstract fun getHomeDao(): HomeScreenDao

}
