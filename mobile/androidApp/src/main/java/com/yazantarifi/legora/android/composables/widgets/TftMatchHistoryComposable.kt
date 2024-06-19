package com.yazantarifi.legora.android.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.response.LegoraTftMatch

@Composable
fun TftMatchHistoryComposable(match: LegoraTftMatch) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(
                    text = "TFT Game",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(17f, TextUnitType.Sp),
                    modifier = Modifier
                )

                Text(
                    text = match.date ?: "",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(15f, TextUnitType.Sp),
                    modifier = Modifier
                )
            }

            Text(
                text = "${match.placement}th Place",
                color = Color.Black,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .background(Color.Yellow)
                    .padding(10.dp)
            )
        }

        LazyRow {
            items(match.getSortedUnitList()) { unit ->
                Column(modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)) {
                    GlideImage(
                        model = unit.image,
                        contentDescription = "Unit Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        unit.items?.forEach {
                            Box(modifier = Modifier.padding(vertical = 5.dp).padding(end = 5.dp)) {
                                GlideImage(
                                    model = it,
                                    contentDescription = "Item Image",
                                    modifier = Modifier
                                        .size(15.dp)
                                        .clip(RoundedCornerShape(5.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}