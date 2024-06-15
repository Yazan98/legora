package com.yazantarifi.legora.android

import android.content.Intent
import androidx.multidex.MultiDexApplication
import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.LegoraRequestListener
import com.yazantarifi.legora.context.LegoraStorageProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class LegoraApplication: MultiDexApplication(), LegoraRequestListener {

    @Inject
    lateinit var storage: LegoraStorageProvider

    override fun onCreate() {
        super.onCreate()
        LegoraSharedStorage.requestsListener = this
    }

    override fun getRequestHeaders(): HashMap<String, String> {
        return LegoraSharedStorage.getApplicationHeaders(
            "1.0",
            storage.getAccessToken()
        )
    }

    override fun onUnAutherizedUser() {
        val pm = packageManager
        val intent = pm.getLaunchIntentForPackage(packageName)
        intent?.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}