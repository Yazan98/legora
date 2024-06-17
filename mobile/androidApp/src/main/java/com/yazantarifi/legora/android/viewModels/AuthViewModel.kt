package com.yazantarifi.legora.android.viewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.legora.android.R
import com.yazantarifi.legora.api.requests.LoginAccountRequestManager
import com.yazantarifi.legora.api.requests.RegisterAccountRequestManager
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.models.LoginRequestBody
import com.yazantarifi.legora.models.RegisterAccountRequestBody
import com.yazantarifi.legora.response.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginRequestManager: LoginAccountRequestManager,
    private val registerRequestManager: RegisterAccountRequestManager,
    private val storageProvider: LegoraStorageProvider,
    @ApplicationContext private val context: Context
): ViewModel() {

    // Global Variables
    val isLoading by lazy { mutableStateOf(false) }
    val isSuccessScreenNavigation by lazy { mutableStateOf(false) }

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
        onChangeLoadingState(true)
        loginRequestManager.getRequestInfo(
            LoginRequestBody(emailState.value, passwordState.value),
            onSuccess = {
                onChangeLoadingState(false)
                it.data?.let { it1 -> onAccountInfo(it1) }
            },
            onError = {
                onShowErrorMessage()
            }
        )
    }

    fun onRegisterAccount() {
        onChangeLoadingState(true)
        registerRequestManager.getRequestInfo(
            RegisterAccountRequestBody(registerEmailState.value, registerPasswordState.value, registerUsernameState.value, registerRegionState.value, registerServerState.value),
            onSuccess =  {
                onChangeLoadingState(false)
                it.data?.let { it1 -> onAccountInfo(it1) }
            },
            onError = {
                onShowErrorMessage()
            }
        )
    }

    private fun onChangeLoadingState(isLoading: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            this@AuthViewModel.isLoading.value = isLoading
        }
    }

    private fun onShowErrorMessage() {
        onChangeLoadingState(false)
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(context, context.getString(R.string.failed_to_auth), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAccountInfo(account: AuthResponse) {
        viewModelScope.launch(Dispatchers.Main) {
            account.auth?.accessToken?.let { storageProvider.updateAccessToken(it) }
            isSuccessScreenNavigation.value = true
        }
    }

}
