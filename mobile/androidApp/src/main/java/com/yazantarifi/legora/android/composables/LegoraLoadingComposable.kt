package com.yazantarifi.legora.android.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yazantarifi.legora.android.R

@Composable
fun LegoraLoadingComposable() {
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.loading
            )
        )

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )


        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
        )
    }
}