package com.yazantarifi.legora.context

import platform.darwin.NSObject
import platform.Foundation.NSUserDefaults

actual typealias LegoraContext = NSObject

actual fun LegoraContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

actual fun LegoraContext.getInt(key: String, default: Int): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

actual fun LegoraContext.putString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun LegoraContext.putLong(key: String, value: Long) {
    NSUserDefaults.standardUserDefaults.setDouble(value.toDouble(), key)
}

actual fun LegoraContext.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}

actual fun LegoraContext.getLong(key: String): Long? {
    return NSUserDefaults.standardUserDefaults.doubleForKey(key).toLong()
}

actual fun LegoraContext.putBool(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

actual fun LegoraContext.getBool(key: String, default: Boolean): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key)
}
