package com.yazantarifi.legora

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.yazantarifi.legora.caching.LegoraDatabase
import com.yazantarifi.legora.caching.instantiateImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

object LegoraIosDatabaseManager {
    fun getDatabaseBuilder(): LegoraDatabase {
        val dbFilePath = NSHomeDirectory() + "/LegoraDatabase.db"
        return Room.databaseBuilder<LegoraDatabase>(
            name = dbFilePath,
            factory = { LegoraDatabase::class.instantiateImpl() }
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}