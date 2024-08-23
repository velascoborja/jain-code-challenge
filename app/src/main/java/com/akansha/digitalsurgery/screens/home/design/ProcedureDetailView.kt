package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.akansha.digitalsurgery.Constants.CARD_IMAGE_HEIGHT
import com.akansha.digitalsurgery.Constants.CREATION_DATE_LABEL
import com.akansha.digitalsurgery.Constants.DURATION_LABEL
import com.akansha.digitalsurgery.Constants.MINUTES
import com.akansha.digitalsurgery.Constants.PHASES_LABEL
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme.spacings

@Composable
fun ProcedureDetailView(
    detail: ProcedureDetailCard,
    onFavouriteStateUpdate: (ProcedureDetailCard, Boolean) -> Unit
) {

    var isFavourite by remember(detail.id) { mutableStateOf(detail.isFavourite) }

    Column(modifier = Modifier.padding(spacings.l)) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                model = detail.cardImageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(CARD_IMAGE_HEIGHT.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(spacings.l))
        Row {
            Text(
                text = detail.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = {
                isFavourite = !isFavourite
                detail.isFavourite = isFavourite
                onFavouriteStateUpdate(detail, isFavourite)
            }) {
                Image(
                    imageVector = if (isFavourite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    }, contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(spacings.l))
        Row {
            Text(
                text = DURATION_LABEL,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${detail.duration} $MINUTES",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(spacings.s))
        Row {
            Text(
                text = CREATION_DATE_LABEL,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = detail.creationDate,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(spacings.l))
        Text(
            text = PHASES_LABEL,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(spacings.s))
        LazyRow {
            items(detail.phases) {
                PhaseListItem(it)
            }
        }
    }
}