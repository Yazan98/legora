package com.yazantarifi.legora.context

import android.app.Application
import android.content.Context

actual typealias LegoraContext = Application

actual fun LegoraContext.putInt(key: String, value: Int) {
    getSpEditor().putInt(key, value).apply()
}

actual fun LegoraContext.getInt(key: String, default: Int): Int {
    return  getSp().getInt(key, default )
}

actual fun LegoraContext.putString(key: String, value: String) {
    getSpEditor().putString(key, value).apply()
}

actual fun LegoraContext.getString(key: String): String? {
    return  getSp().getString(key, null)
}

actual fun LegoraContext.putBool(key: String, value: Boolean) {
    getSpEditor().putBoolean(key, value).apply()
}

actual fun LegoraContext.getBool(key: String, default: Boolean): Boolean {
    return getSp().getBoolean(key, default)
}

private fun LegoraContext.getSp() = getSharedPreferences("legora_keys", Context.MODE_PRIVATE)

private fun LegoraContext.getSpEditor() = getSp().edit()
