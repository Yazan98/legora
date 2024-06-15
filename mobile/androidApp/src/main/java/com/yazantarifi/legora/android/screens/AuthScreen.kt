package com.yazantarifi.legora.android.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yazantarifi.legora.android.ApplicationScreenNavigation
import com.yazantarifi.legora.android.fragments.LoginScreen
import com.yazantarifi.legora.android.fragments.RegisterScreen
import com.yazantarifi.legora.android.screens.base.LegoraBaseScreen
import com.yazantarifi.legora.android.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthScreen: LegoraBaseScreen() {

    @Composable
    override fun OnStartScreenContent(scaffoldState: ScaffoldState, paddingValues: PaddingValues) {
        val viewModel: AuthViewModel = hiltViewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ApplicationScreenNavigation.LOGIN.key) {
            composable(ApplicationScreenNavigation.LOGIN.key) {
                LoginScreen(viewModel, navController)
            }

            composable(ApplicationScreenNavigation.REGISTER.key) {
                RegisterScreen(viewModel = viewModel)
            }
        }
    }

}
