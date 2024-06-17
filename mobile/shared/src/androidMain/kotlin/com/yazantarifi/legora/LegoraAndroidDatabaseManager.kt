package com.yazantarifi.legora

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yazantarifi.legora.caching.LegoraDatabase

object LegoraAndroidDatabaseManager {

    fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<LegoraDatabase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("legora.db")
        return Room.databaseBuilder<LegoraDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

}