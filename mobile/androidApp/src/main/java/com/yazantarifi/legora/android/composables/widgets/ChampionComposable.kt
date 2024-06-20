package com.yazantarifi.legora.android.composables.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.response.LegoraChampion

@Composable
fun ChampionComposable(champion: LegoraChampion, screenWidth: Dp) {
    val isChampionLol = champion.type == "lol"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        GlideImage(
            model = champion.icon,
            contentDescription = "Champion Icon",
            modifier = if (isChampionLol) Modifier.size(60.dp).clip(CircleShape) else Modifier.width((screenWidth - 30.dp) / 2).height(120.dp).clip(
                RoundedCornerShape(10.dp)
            ),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = champion.name ?: "",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = TextUnit(16f, TextUnitType.Sp),
        )
    }
}
