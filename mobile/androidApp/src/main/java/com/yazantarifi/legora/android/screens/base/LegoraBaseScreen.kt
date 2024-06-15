package com.yazantarifi.legora.android.screens.base

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.yazantarifi.legora.android.LegoraAppTheme
import com.yazantarifi.legora.android.R

abstract class LegoraBaseScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isEdgesEnabled()) {
            enableEdgeToEdge()
        }

        setContent {
            val scaffoldState = rememberScaffoldState()
            LegoraAppTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
                    topBar = {
                        if (!TextUtils.isEmpty(getToolbarTitle())) {
                            CenterAlignedTopAppBar(title = {
                                Text(text = getToolbarTitle(),  color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                            }, navigationIcon = {
                                if (isBackButtonEnabled()) {
                                    IconButton(onClick = { (this as? ComponentActivity)?.onBackPressedDispatcher?.onBackPressed() }) {
                                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)
                                    }
                                }
                            })
                        }
                    },
                    contentColor = MaterialTheme.colorScheme.background,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    drawerContentColor = MaterialTheme.colorScheme.background
                ) {
                    OnStartScreenContent(scaffoldState, it)
                }

                OnScreenStarted()
            }
        }
    }

    open fun isEdgesEnabled(): Boolean {
        return true
    }

    open fun isBackButtonEnabled(): Boolean {
        return true
    }

    open fun getToolbarTitle(): String {
        return getString(R.string.app_name)
    }

    @Composable
    abstract fun OnStartScreenContent(scaffoldState: ScaffoldState, paddingValues: PaddingValues)

    @Composable
    open fun OnScreenStarted() = Unit

}