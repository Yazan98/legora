package com.yazantarifi.legora.android.composables.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.home.items.HomeUserWidget

@Composable
fun HomeUserWidgetComposable(item: HomeUserWidget) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        GlideImage(model = item.image,
            contentDescription = "Riot Icon",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(text = "Welcome Back to Legora", color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item.name, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = item.server, color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}