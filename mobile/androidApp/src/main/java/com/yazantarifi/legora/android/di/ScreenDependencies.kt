package com.yazantarifi.legora.android.di

import android.content.Context
import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.account.LegoraAccountItemsProvider
import com.yazantarifi.legora.android.LegoraApplication
import com.yazantarifi.legora.api.requests.GetHomeFeedRequestManager
import com.yazantarifi.legora.api.requests.LoginAccountRequestManager
import com.yazantarifi.legora.api.requests.RegisterAccountRequestManager
import com.yazantarifi.legora.caching.dao.ChampionsDao
import com.yazantarifi.legora.champions.LegoraChampionsItemsProvider
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.home.HomeScreenItemsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient

@Module
@InstallIn(ViewModelComponent::class)
object ScreenDependencies {

    @Provides
    fun getLoginAccountRequestManager(httpClient: HttpClient, storageProvider: LegoraStorageProvider): LoginAccountRequestManager {
        return LoginAccountRequestManager(httpClient, LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    @Provides
    fun getRegisterAccountRequestManager(httpClient: HttpClient, storageProvider: LegoraStorageProvider): RegisterAccountRequestManager {
        return RegisterAccountRequestManager(httpClient, LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    @Provides
    fun getGetHomeFeedRequestManager(httpClient: HttpClient, storageProvider: LegoraStorageProvider): GetHomeFeedRequestManager {
        return GetHomeFeedRequestManager(httpClient, LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    @Provides
    fun getHomeScreenItemsProvider(
        httpClient: HttpClient,
        storageProvider: LegoraStorageProvider,
    ): HomeScreenItemsProvider {
        return HomeScreenItemsProvider(httpClient, storageProvider)
    }

    @Provides
    fun getLegoraAccountItemsProvider(httpClient: HttpClient, storageProvider: LegoraStorageProvider): LegoraAccountItemsProvider {
        return LegoraAccountItemsProvider(httpClient, storageProvider)
    }

    @Provides
    fun getLegoraChampionsItemsProvider(httpClient: HttpClient, storageProvider: LegoraStorageProvider, dao: ChampionsDao): LegoraChampionsItemsProvider {
        return LegoraChampionsItemsProvider(httpClient, dao, storageProvider)
    }

}
