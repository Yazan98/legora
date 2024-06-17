package com.yazantarifi.legora.context

expect fun LegoraContext.putInt(key: String, value: Int)

expect fun LegoraContext.getInt(key: String, default: Int): Int

expect fun LegoraContext.putString(key: String, value: String)

expect fun LegoraContext.putLong(key: String, value: Long)

expect fun LegoraContext.getString(key: String) : String?

expect fun LegoraContext.getLong(key: String) : Long?

expect fun LegoraContext.putBool(key: String, value: Boolean)

expect fun LegoraContext.getBool(key: String, default: Boolean): Boolean
