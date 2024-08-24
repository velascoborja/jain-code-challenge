package com.akansha.digitalsurgery.screens.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.akansha.digitalsurgery.Constants.EMPTY_FAV_SCREEN_MESSAGE
import com.akansha.digitalsurgery.ui.theme.Spacings

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacings.l),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = EMPTY_FAV_SCREEN_MESSAGE,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}