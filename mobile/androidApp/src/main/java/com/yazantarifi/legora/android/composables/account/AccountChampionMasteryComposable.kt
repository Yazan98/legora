package com.yazantarifi.legora.android.composables.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.response.AccountMasteryChampion

@Composable
fun AccountChampionMasteryComposable(champion: AccountMasteryChampion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        GlideImage(
            model = champion.icon ?: "",
            contentDescription = "Champion Logo",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = champion.name ?: "", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            Text(text = "Level : ${champion.level} - Points : ${champion.getPointsValue()}", color = MaterialTheme.colorScheme.onSecondary, fontWeight = FontWeight.Bold)
        }
    }
}