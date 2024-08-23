package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.screens.home.ProcedureViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismissRequest: () -> Unit,
    onFavouriteStateUpdate: (ProcedureDetailCard, Boolean) -> Unit
) {
    val viewModel = hiltViewModel<ProcedureViewModel>()
    val procedureDetail: ProcedureDetailCard? by viewModel.procedureDetailLiveData.observeAsState()

    procedureDetail?.let {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                ProcedureDetailView(detail = it, onFavouriteStateUpdate = onFavouriteStateUpdate)
            }
        }
    }
}
