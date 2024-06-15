package com.yazantarifi.legora.android.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yazantarifi.legora.api.requests.LoginAccountRequestManager
import com.yazantarifi.legora.api.requests.RegisterAccountRequestManager
import com.yazantarifi.legora.context.LegoraStorageProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginRequestManager: LoginAccountRequestManager,
    private val registerRequestManager: RegisterAccountRequestManager,
    private val storageProvider: LegoraStorageProvider
): ViewModel() {

    // Global Variables
    val isLoading by lazy { mutableStateOf(false) }

    // Login Variables
    val emailState by lazy { mutableStateOf("") }
    val passwordState by lazy { mutableStateOf("") }


    // Register Variables
    val registerEmailState by lazy { mutableStateOf("") }
    val registerPasswordState by lazy { mutableStateOf("") }
    val registerServerState by lazy { mutableStateOf("") }
    val registerUsernameState by lazy { mutableStateOf("") }
    val registerRegionState by lazy { mutableStateOf("") }

    fun onLogin() {

    }

    fun onRegisterAccount() {

    }

}
