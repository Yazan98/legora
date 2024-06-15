package com.yazantarifi.legora.android.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.android.ApplicationScreenNavigation
import com.yazantarifi.legora.android.R
import com.yazantarifi.legora.android.TertiaryColorLight
import com.yazantarifi.legora.android.composables.LegoraLoadingComposable
import com.yazantarifi.legora.android.composables.LegoraTextInputComposable
import com.yazantarifi.legora.android.viewModels.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navHostController: NavHostController
) {
    if (viewModel.isLoading.value) {
        LegoraLoadingComposable()
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                GlideImage(
                    model = R.drawable.toolbar_logo,
                    contentDescription = "Riot Logo",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Inside
                )

                Text(
                    text = "Sign In",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(20f, TextUnitType.Sp),
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                val emailState = remember { viewModel.emailState }
                LegoraTextInputComposable(
                    emailState,
                    stringResource(id = R.string.email),
                    stringResource(id = R.string.email_placeholder),
                    false,
                    true,
                    1
                ) {
                    emailState.value = it
                    viewModel.emailState.value = it
                }

                val passwordState = remember { viewModel.passwordState }
                LegoraTextInputComposable(
                    passwordState,
                    stringResource(id = R.string.password),
                    stringResource(id = R.string.password_placeholder),
                    true,
                    true,
                    1
                ) {
                    passwordState.value = it
                    viewModel.passwordState.value = it
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                if (viewModel.emailState.value.isNotEmpty() && viewModel.passwordState.value.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(TertiaryColorLight)
                            .border(2.dp, TertiaryColorLight, RoundedCornerShape(20.dp))
                            .clickable {
                                viewModel.onLogin()
                            }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(70.dp)
                            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Register New Account",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(15f, TextUnitType.Sp),
                    modifier = Modifier.padding(10.dp).clickable { navHostController.navigate(ApplicationScreenNavigation.REGISTER.key) }
                )

                Spacer(modifier = Modifier
                    .height(40.dp))
            }
        }
    }
}