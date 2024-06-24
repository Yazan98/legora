package com.yazantarifi.legora.android.di

import android.app.Application
import android.content.Context
import com.yazantarifi.legora.LegoraAndroidDatabaseManager
import com.yazantarifi.legora.api.LegoraHttpClient
import com.yazantarifi.legora.caching.LegoraDatabase
import com.yazantarifi.legora.caching.dao.HomeScreenDao
import com.yazantarifi.legora.context.LegoraStorageKeyValue
import com.yazantarifi.legora.context.LegoraStorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationDependencies {

    @Provides
    @Singleton
    fun getLegoraStorageProvider(@ApplicationContext context: Context): LegoraStorageProvider {
        return LegoraStorageProvider(LegoraStorageKeyValue(context as Application))
    }

    @Provides
    @Singleton
    fun getHttpClient(): HttpClient {
        return LegoraHttpClient().httpClient
    }

    @Provides
    @Singleton
    fun getLegoraAndroidDatabaseManager(@ApplicationContext context: Context): LegoraDatabase {
        return LegoraAndroidDatabaseManager
            .getDatabaseBuilder(context)
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @Provides
    @Singleton
    fun getHomeScreenDao(database: LegoraDatabase): HomeScreenDao {
        return database.getHomeDao()
    }

}