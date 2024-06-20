package com.yazantarifi.legora.android.di

import android.content.Context
import com.yazantarifi.legora.account.LegoraAccountItemsProvider
import com.yazantarifi.legora.android.LegoraApplication
import com.yazantarifi.legora.api.requests.GetHomeFeedRequestManager
import com.yazantarifi.legora.api.requests.LoginAccountRequestManager
import com.yazantarifi.legora.api.requests.RegisterAccountRequestManager
import com.yazantarifi.legora.caching.dao.HomeScreenDao
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
    fun getLoginAccountRequestManager(httpClient: HttpClient): LoginAccountRequestManager {
        return LoginAccountRequestManager(httpClient)
    }

    @Provides
    fun getRegisterAccountRequestManager(httpClient: HttpClient): RegisterAccountRequestManager {
        return RegisterAccountRequestManager(httpClient)
    }

    @Provides
    fun getGetHomeFeedRequestManager(httpClient: HttpClient): GetHomeFeedRequestManager {
        return GetHomeFeedRequestManager(httpClient)
    }

    @Provides
    fun getHomeScreenItemsProvider(
        @ApplicationContext context: Context,
        httpClient: HttpClient,
        storageProvider: LegoraStorageProvider,
        homeScreenDao: HomeScreenDao
    ): HomeScreenItemsProvider {
        return HomeScreenItemsProvider(httpClient, storageProvider, homeScreenDao, context as LegoraApplication)
    }

    @Provides
    fun getLegoraAccountItemsProvider(httpClient: HttpClient): LegoraAccountItemsProvider {
        return LegoraAccountItemsProvider(httpClient)
    }

    @Provides
    fun getLegoraChampionsItemsProvider(httpClient: HttpClient): LegoraChampionsItemsProvider {
        return LegoraChampionsItemsProvider(httpClient)
    }

}
