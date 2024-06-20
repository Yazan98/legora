package com.yazantarifi.legora.android.composables.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            modifier = Modifier.fillMaxWidth().height(210.dp),
            contentScale = ContentScale.Crop
        )

        GlideImage(
            model = header?.summonerInfo?.profileImage ?: "",
            contentDescription = "Summoner Profile Image",
            modifier = Modifier.size(80.dp).offset(y = 100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }


}