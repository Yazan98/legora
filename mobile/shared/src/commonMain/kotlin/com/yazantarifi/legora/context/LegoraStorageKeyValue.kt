package com.yazantarifi.legora.context

class LegoraStorageKeyValue constructor(private val context: LegoraContext) {

    fun put(key: String, value: Int) {
        context.putInt(key, value)
    }

    fun put(key: String, value: String) {
        context.putString(key, value)
    }

    fun put(key: String, value: Long) {
        context.putLong(key, value)
    }

    fun put(key: String, value: Boolean) {
        context.putBool(key, value)
    }

    fun getInt(key: String, default: Int): Int = context.getInt(key, default)


    fun getString(key: String) : String? = context.getString(key)

    fun getLong(key: String) : Long? = context.getLong(key)


    fun getBool(key: String, default: Boolean): Boolean = context.getBool(key, default)

}
