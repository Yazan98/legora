package com.yazantarifi.legora

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.yazantarifi.legora.caching.LegoraDatabase
import platform.Foundation.NSHomeDirectory

object LegoraIosDatabaseManager {
    fun getDatabaseBuilder(): RoomDatabase.Builder<LegoraDatabase> {
        val dbFilePath = NSHomeDirectory() + "/LegoraDatabase"
        return Room.databaseBuilder<LegoraDatabase>(
            name = dbFilePath,
            factory = { LegoraDatabase::class.instantiateImpl() }
        ).setDriver(BundledSQLiteDriver())
    }
}