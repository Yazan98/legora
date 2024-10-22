package com.yazantarifi.legora.caching.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazantarifi.legora.caching.models.ChampionCacheEntity

@Dao
interface ChampionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun onInsertChampions(championCacheEntity: List<ChampionCacheEntity>)

    @Query("SELECT * FROM champions")
    suspend fun getChampions(): List<ChampionCacheEntity>

}
