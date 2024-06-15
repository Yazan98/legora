package com.yazantarifi.legora.api


interface LegoraRequestListener {

    fun getRequestHeaders(): HashMap<String, String>

    fun onUnAutherizedUser()

}
