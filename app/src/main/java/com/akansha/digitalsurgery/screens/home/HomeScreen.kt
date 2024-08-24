package com.akansha.digitalsurgery.screens.home

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
import com.akansha.digitalsurgery.Constants.DEFAULT_BOOL
import com.akansha.digitalsurgery.Constants.DEFAULT_STRING
import com.akansha.digitalsurgery.screens.common.design.BottomSheet
import com.akansha.digitalsurgery.screens.common.design.ProcedureListItem
import com.akansha.digitalsurgery.screens.common.model.DigitalSurgeryScreen
import com.akansha.digitalsurgery.screens.common.model.FavouriteState
import com.akansha.digitalsurgery.screens.common.model.getFavouriteState
import com.akansha.digitalsurgery.screens.viewmodel.ProcedureViewModel
import com.akansha.digitalsurgery.ui.theme.Spacings


@Composable
fun HomeScreen(showSnackBar: (String, FavouriteState) -> Unit) {
    Column {
        val viewModel = hiltViewModel<ProcedureViewModel>()
        val procedures = viewModel.procedureLiveData.observeAsState(emptyList())
        val favorites = viewModel.favouritesLiveData.observeAsState()
        var showBottomSheet by remember { mutableStateOf(DEFAULT_BOOL) }
        var clickedItemId by remember { mutableStateOf(DEFAULT_STRING) }

        LaunchedEffect(key1 = Unit) {
            viewModel.getProcedures()
        }

        LaunchedEffect(key1 = clickedItemId, key2 = favorites.value) {
            if (clickedItemId.isNotEmpty()) {
                viewModel.getProcedureDetails(clickedItemId)
            }
        }

        LazyColumn(modifier = Modifier.padding(Spacings.s)) {
            items(procedures.value) { item ->
                ProcedureListItem(
                    procedure = item, onItemClick = {
                        clickedItemId = it
                        showBottomSheet = true
                    },
                    onFavouriteStateUpdate = { procedure, isFavourite ->
                        viewModel.onFavouriteStateUpdate(procedure.id, isFavourite)
                        showSnackBar(procedure.title, getFavouriteState(isFavourite))
                    },
                    DigitalSurgeryScreen.HOME
                )
            }
        }

        if (showBottomSheet) {
            BottomSheet(
                onDismissRequest = { showBottomSheet = false },
                onFavouriteStateUpdate = { detail, isFavourite ->
                    viewModel.onFavouriteStateUpdate(detail.id, isFavourite)
                    showSnackBar(detail.title, getFavouriteState(isFavourite))
                    viewModel.updateProcedureItems(detail.id, isFavourite)
                },
            )
        }
    }
}
