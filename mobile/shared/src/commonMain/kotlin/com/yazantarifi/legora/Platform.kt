package com.yazantarifi.legora

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform