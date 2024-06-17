package com.yazantarifi.legora.caching.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazantarifi.legora.caching.models.HomeScreenWidgetEntity

@Dao
interface HomeScreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun onInsertHomeWidgets(vararg widgets: HomeScreenWidgetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun onInsertHomeWidgets(widgets: List<HomeScreenWidgetEntity>)

    @Delete
    fun deleteAllHomeWidgets(item: HomeScreenWidgetEntity)

    @Query("SELECT * FROM HomeScreenWidgetEntity")
    fun getHomeWidgets(): List<HomeScreenWidgetEntity>

}
