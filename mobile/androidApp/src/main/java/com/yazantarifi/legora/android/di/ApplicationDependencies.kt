package com.yazantarifi.legora.android.di

import android.content.Context
import com.yazantarifi.legora.api.LegoraHttpClient
import com.yazantarifi.legora.context.LegoraContext
import com.yazantarifi.legora.context.LegoraStorageKeyValue
import com.yazantarifi.legora.context.LegoraStorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationDependencies {

    @Provides
    @Singleton
    fun getLegoraStorageProvider(@ApplicationContext context: Context): LegoraStorageProvider {
        return LegoraStorageProvider(LegoraStorageKeyValue(context as LegoraContext))
    }

    @Provides
    @Singleton
    fun getHttpClient(): HttpClient {
        return LegoraHttpClient().httpClient
    }


}