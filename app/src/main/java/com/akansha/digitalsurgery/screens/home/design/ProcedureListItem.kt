package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.akansha.digitalsurgery.Constants.IMAGE_WIDTH
import com.akansha.digitalsurgery.Constants.PHASE_COUNT_LABEL
import com.akansha.digitalsurgery.Constants.PROCEDURE_ITEM_HEIGHT
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme.spacings


@Composable
fun ProcedureListItem(
    procedure: ProcedureItem, onItemClick: (String) -> Unit,
    isFavourite: Boolean = false, onFavouriteStateUpdate: (String, Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = spacings.s)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .height(PROCEDURE_ITEM_HEIGHT.dp)
            .clickable { onItemClick(procedure.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = procedure.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.width(IMAGE_WIDTH.dp)
        )
        Spacer(modifier = Modifier.width(spacings.s))
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            Text(
                text = procedure.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(spacings.s))
            Text(
                text = "$PHASE_COUNT_LABEL ${procedure.phaseCount}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = { onFavouriteStateUpdate(procedure.id, !isFavourite) }) {
            Image(
                imageVector = if (isFavourite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                }, contentDescription = null
            )
        }
    }
}
