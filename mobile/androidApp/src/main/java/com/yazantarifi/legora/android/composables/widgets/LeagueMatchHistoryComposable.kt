package com.yazantarifi.legora.android.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.response.LegoraMatch

@Composable
fun LeagueMatchHistoryComposable(match: LegoraMatch) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = match.mode ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(17f, TextUnitType.Sp),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = match.creationTimestamp ?: "",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                )
            }

            Text(
                text = if (match.isVictory == true) "Victory" else "Defeat",
                color = Color.White,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .background(if (match.isVictory == true) Color.Blue else Color.Red)
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(60.dp)
            ) {
                GlideImage(
                    model = match.champion?.image ?: "",
                    contentDescription = "Match Champion",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = match.champion?.name ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = TextUnit(17f, TextUnitType.Sp),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Column {
                (match.items?.chunked(3) ?: arrayListOf()).forEach {
                    Row {
                        it.forEach { item ->
                            Box(modifier = Modifier.padding(5.dp)) {
                                GlideImage(model = item, contentDescription = "Item", modifier = Modifier
                                    .size(25.dp)
                                    .clip(
                                        RoundedCornerShape(5.dp)
                                    ))
                            }
                        }
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            Text(
                text = "${match.kills}/${match.deaths}/${match.assists} Score",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = TextUnit(15f, TextUnitType.Sp),
            )

            Text(
                text = "${match.farm} Farm",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = TextUnit(15f, TextUnitType.Sp),
            )

            Text(
                text = "${match.getGoldValue()} Gold",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = TextUnit(15f, TextUnitType.Sp),
            )
        }
    }
}