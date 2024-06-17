package com.yazantarifi.legora.android

import android.content.Intent
import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.LegoraRequestListener
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.home.LegoraJsonConverterListener
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlin.reflect.KClass


@HiltAndroidApp
class LegoraApplication: MultiDexApplication(), LegoraRequestListener, LegoraJsonConverterListener {

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

    override fun <T : Any> onParseJsonObject(jsonValue: String, type: KClass<T>): T {
        return Gson().fromJson(jsonValue, type::class.java) as T
    }

    override fun <T> getJsonString(value: T): String {
        return Gson().toJson(value)
    }

    override fun onUnAutherizedUser() {
        val pm = packageManager
        val intent = pm.getLaunchIntentForPackage(packageName)
        intent?.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}