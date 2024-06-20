package com.yazantarifi.legora.android.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yazantarifi.legora.account.AccountItemType
import com.yazantarifi.legora.account.AccountTitle
import com.yazantarifi.legora.android.composables.LegoraLoadingComposable
import com.yazantarifi.legora.android.composables.account.AccountHeaderComposable
import com.yazantarifi.legora.android.composables.home.HomeTitleWidgetComposable
import com.yazantarifi.legora.android.composables.widgets.LeagueMatchHistoryComposable
import com.yazantarifi.legora.android.composables.widgets.TftMatchHistoryComposable
import com.yazantarifi.legora.android.viewModels.HomeViewModel
import com.yazantarifi.legora.response.AccountInfoResponse
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraTftMatch

@Composable
fun ProfileScreenComposable(viewModel: HomeViewModel) {
    LaunchedEffect(true) {
        viewModel.getAccountInfo()
    }

    if (viewModel.isLoading.value) {
        LegoraLoadingComposable()
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()) {
            items(viewModel.accountInfoState) { item ->
                when (item.getType()) {
                    AccountItemType.TITLE -> Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)) {
                        HomeTitleWidgetComposable((item as AccountTitle).title)
                    }

                    AccountItemType.LOL_MATCH_HISTORY -> Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)) {
                        LeagueMatchHistoryComposable(item as LegoraMatch)
                    }

                    AccountItemType.TFT_MATCH_HISTORY -> Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)) {
                        TftMatchHistoryComposable(item as LegoraTftMatch)
                    }

                    AccountItemType.HEADER -> AccountHeaderComposable(item as AccountInfoResponse)
                    else -> Text(text = "Widget")
                }
            }
        }
    }
}