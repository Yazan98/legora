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
import com.yazantarifi.legora.android.composables.home.HomeTitleWidgetComposable
import com.yazantarifi.legora.android.viewModels.HomeViewModel
import com.yazantarifi.legora.home.items.HomeScreenItemType
import com.yazantarifi.legora.home.items.HomeTitleWidget

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
                when (item.getType()) {
                    HomeScreenItemType.TITLE_WIDGET -> HomeTitleWidgetComposable(title = (item as HomeTitleWidget).title)
                    else -> Text(text = "${item.getType()}", color = Color.Black)
                }
            }
        }
    }
}
