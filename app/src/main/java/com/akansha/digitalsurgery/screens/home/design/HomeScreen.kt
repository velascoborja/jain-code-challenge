package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akansha.digitalsurgery.screens.home.ProcedureViewModel
import com.akansha.digitalsurgery.screens.home.model.DigitalSurgeryScreen
import com.akansha.digitalsurgery.screens.home.model.FavouriteState
import com.akansha.digitalsurgery.ui.theme.Spacings


@Composable
fun HomeScreen(showSnackBar: (String, FavouriteState) -> Unit) {
    Column {
        val viewModel = hiltViewModel<ProcedureViewModel>()
        val procedures = viewModel.procedureLiveData.observeAsState(emptyList())
        val favorites = viewModel.favouritesLiveData.observeAsState()
        var showBottomSheet by remember { mutableStateOf(false) }
        var clickedItemId by remember { mutableStateOf("") }

        LaunchedEffect(key1 = Unit) {
            viewModel.getProcedures()
        }

        LaunchedEffect(key1 = clickedItemId, key2 = favorites.value) {
            if (clickedItemId.isNotEmpty()) {
                viewModel.getProcedureDetails(clickedItemId)
            }
        }

        LazyColumn(modifier = Modifier.padding(Spacings.s)) {
            items(procedures.value, key = { it.id }) { item ->
                ProcedureListItem(
                    procedure = item, onItemClick = {
                        clickedItemId = it
                        showBottomSheet = true
                    },
                    onFavouriteStateUpdate = { procedure, isFavourite ->
                        viewModel.onFavouriteStateUpdate(

                            procedure.id,
                            isFavourite,
                        )
                        showSnackBar(
                            procedure.title,
                            if (isFavourite) FavouriteState.ADDED
                            else FavouriteState.REMOVED
                        )
                    },
                    DigitalSurgeryScreen.Home
                )
            }
        }

        if (showBottomSheet) {
            BottomSheet(
                onDismissRequest = { showBottomSheet = false },
                onFavouriteStateUpdate = { detail, isFavourite ->
                    viewModel.onFavouriteStateUpdate(
                        detail.id,
                        isFavourite,
                    )
                    showSnackBar(
                        detail.title,
                        if (isFavourite) FavouriteState.ADDED
                        else FavouriteState.REMOVED
                    )
                    viewModel.updateProcedureItems(
                        detail.id,
                        isFavourite
                    )
                },
            )
        }
    }
}
