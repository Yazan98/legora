package com.yazantarifi.legora.home

import kotlin.reflect.KClass

interface LegoraJsonConverterListener {

    fun <T> getJsonString(value: T): String

    fun <T: Any> onParseJsonObject(jsonValue: String, type: KClass<T>): T

}
