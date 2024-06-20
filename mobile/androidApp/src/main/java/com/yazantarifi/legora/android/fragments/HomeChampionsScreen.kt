package com.yazantarifi.legora.android.fragments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.yazantarifi.legora.android.composables.LegoraLoadingComposable
import com.yazantarifi.legora.android.composables.home.HomeTitleWidgetComposable
import com.yazantarifi.legora.android.composables.widgets.ChampionComposable
import com.yazantarifi.legora.android.composables.widgets.ChampionsFreeToPlayComposable
import com.yazantarifi.legora.android.viewModels.HomeViewModel
import com.yazantarifi.legora.champions.ChampionItemType
import com.yazantarifi.legora.champions.widgets.ChampionTitleWidget
import com.yazantarifi.legora.champions.widgets.ChampionsFreeToPlayWidget
import com.yazantarifi.legora.response.LegoraChampion

@Composable
fun HomeChampionsScreen(viewModel: HomeViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    LaunchedEffect(true) {
        viewModel.getChampionsItems()
    }

    if (viewModel.isLoading.value) {
        LegoraLoadingComposable()
    } else {
        val lazyGridState = rememberLazyGridState()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            columns = GridCells.Fixed(4),
            state = lazyGridState
        ) {
            items(viewModel.championsItemsState, span = { item ->
                val span = if (item.getType() == ChampionItemType.FREE_TO_PLAY || item.getType() == ChampionItemType.TITLE) {
                    4
                } else {
                    if (item is LegoraChampion && item.type == "lol") {
                        1
                    } else {
                        2
                    }
                }
                GridItemSpan(span)
            }) { item ->
                when (item.getType()) {
                    ChampionItemType.FREE_TO_PLAY -> ChampionsFreeToPlayComposable((item as ChampionsFreeToPlayWidget).champions, screenWidth)
                    ChampionItemType.CHAMPION -> ChampionComposable(champion = item as LegoraChampion, screenWidth)
                    ChampionItemType.TITLE -> HomeTitleWidgetComposable(title = (item as ChampionTitleWidget).title)
                    else -> Text(text = "${item.getType()}", color = Color.Black)
                }
            }
        }
    }
}