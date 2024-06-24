package com.yazantarifi.legora.android.composables.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.response.AccountInfoResponse

@Composable
fun AccountHeaderComposable(header: AccountInfoResponse) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            model = header.summonerInfo?.coverImage ?: "",
            contentDescription = "Summoner Cover Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Crop
        )

        GlideImage(
            model = header?.summonerInfo?.profileImage ?: "",
            contentDescription = "Summoner Profile Image",
            modifier = Modifier
                .size(80.dp)
                .offset(y = 100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 250.dp)
    ) {
        Row {
            Text(
                text = header.summonerInfo?.summonerHighlightName ?: "",
                color =  MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "#${(header.summonerInfo?.serverHighlightName ?: "")}",
                color =  MaterialTheme.colorScheme.onSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = "Mastery Points : ",
                color =  MaterialTheme.colorScheme.onSecondary
            )

            Text(
                text = "${header.summonerInfo?.masteryPoints ?: 0}",
                color =  MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Row {
            Text(
                text = "Level : ",
                color =  MaterialTheme.colorScheme.onSecondary
            )

            Text(
                text = "${header.summonerInfo?.level ?: 0}",
                color =  MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Top Mastery Champions",
            color =  MaterialTheme.colorScheme.onPrimary
        )

        header.summonerInfo?.topChampionsMastery?.forEach {
            AccountChampionMasteryComposable(it)
        }
    }
}