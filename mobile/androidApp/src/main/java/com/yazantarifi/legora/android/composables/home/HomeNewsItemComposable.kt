package com.yazantarifi.legora.android.composables.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.home.items.HomeNewsItemWidget

@Composable
fun HomeNewsItemComposable(item: HomeNewsItemWidget) {
    Column(modifier = Modifier.fillMaxWidth()) {
        GlideImage(
            model = item.item.gameImage,
            contentDescription = "News Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = item.item.description ?: "",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = TextUnit(16f, TextUnitType.Sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}