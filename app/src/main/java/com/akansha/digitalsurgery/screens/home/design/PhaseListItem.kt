package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.akansha.digitalsurgery.Constants.IMAGE_WIDTH
import com.akansha.digitalsurgery.Constants.PHASE_ITEM_HEIGHT
import com.akansha.digitalsurgery.Constants.PHASE_ITEM_WIDTH
import com.akansha.digitalsurgery.model.PhaseDetailCard
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme.spacings

@Composable
fun PhaseListItem(phase: PhaseDetailCard) {
    Row(
        modifier = Modifier
            .padding(end = spacings.s)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .width(PHASE_ITEM_WIDTH.dp)
            .height(PHASE_ITEM_HEIGHT.dp)
    ) {
        AsyncImage(
            model = phase.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.width(IMAGE_WIDTH.dp)
        )
        Spacer(modifier = Modifier.width(spacings.s))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = phase.name,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
