package com.yazantarifi.legora.android.di

import com.yazantarifi.legora.api.requests.LoginAccountRequestManager
import com.yazantarifi.legora.api.requests.RegisterAccountRequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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

}