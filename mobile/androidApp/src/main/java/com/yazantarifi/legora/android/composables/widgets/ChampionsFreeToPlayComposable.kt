package com.yazantarifi.legora.android.composables.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yazantarifi.legora.response.LegoraChampion

@Composable
fun ChampionsFreeToPlayComposable(champions: List<LegoraChampion>, screenWidth: Dp) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        items(champions) { item ->
            ChampionComposable(champion = item, screenWidth)
        }
    }
}