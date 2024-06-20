package com.yazantarifi.legora.android.fragments

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yazantarifi.legora.android.composables.LegoraLoadingComposable
import com.yazantarifi.legora.android.composables.home.HomeNewsItemComposable
import com.yazantarifi.legora.android.composables.home.HomePlayersWidgetComposable
import com.yazantarifi.legora.android.composables.home.HomeTitleWidgetComposable
import com.yazantarifi.legora.android.composables.home.HomeUserWidgetComposable
import com.yazantarifi.legora.android.composables.widgets.LeagueMatchHistoryComposable
import com.yazantarifi.legora.android.composables.widgets.TftMatchHistoryComposable
import com.yazantarifi.legora.android.viewModels.HomeViewModel
import com.yazantarifi.legora.home.items.HomeNewsItemWidget
import com.yazantarifi.legora.home.items.HomePlayersWidget
import com.yazantarifi.legora.home.items.HomeScreenItemType
import com.yazantarifi.legora.home.items.HomeTitleWidget
import com.yazantarifi.legora.home.items.HomeUserWidget
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraTftMatch

@Composable
fun HomeFeedScreen(viewModel: HomeViewModel) {
    LaunchedEffect(true) {
        viewModel.getHomeScreenItems()
    }

    if (viewModel.isLoading.value) {
        LegoraLoadingComposable()
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
            items(viewModel.homeScreenWidgetsState) { item ->
                when (item.getHomeWidgetType()) {
                    HomeScreenItemType.MATCH_HISTORY_TFT -> TftMatchHistoryComposable(item as LegoraTftMatch)
                    HomeScreenItemType.MATCH_HISTORY_LOL -> LeagueMatchHistoryComposable(item as LegoraMatch)
                    HomeScreenItemType.PLAYERS_WIDGET -> HomePlayersWidgetComposable(item as HomePlayersWidget)
                    HomeScreenItemType.USER_WIDGET -> HomeUserWidgetComposable(item as HomeUserWidget)
                    HomeScreenItemType.LATEST_NEWS_WIDGET -> HomeNewsItemComposable(item as HomeNewsItemWidget)
                    HomeScreenItemType.TITLE_WIDGET -> HomeTitleWidgetComposable(title = (item as HomeTitleWidget).title)
                    else -> Text(text = "${item.getHomeWidgetType()}", color = Color.Black)
                }
            }
        }
    }
}
