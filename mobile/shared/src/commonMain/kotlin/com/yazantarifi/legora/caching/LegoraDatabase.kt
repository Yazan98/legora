package com.yazantarifi.legora.caching

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yazantarifi.legora.caching.dao.ChampionsDao
import com.yazantarifi.legora.caching.models.ChampionCacheEntity

@Database(entities = [ChampionCacheEntity::class], version = 1)
//@TypeConverters(Converters::class)
abstract class LegoraDatabase: RoomDatabase() ,DB {

    abstract fun getChampionsDao(): ChampionsDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}