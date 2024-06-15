package com.yazantarifi.legora.response

import kotlinx.serialization.Serializable

@Serializable
data class LegoraResponse<T>(
    val message: String? = "",
    val status: Int? = 0,
    val data: T? = null
)
