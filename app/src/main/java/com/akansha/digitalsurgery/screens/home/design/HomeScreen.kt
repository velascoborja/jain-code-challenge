package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.akansha.digitalsurgery.Constants.APP_NAME
import com.akansha.digitalsurgery.screens.home.ProcedureViewModel
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme
import com.akansha.digitalsurgery.ui.theme.Spacings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = APP_NAME) }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val viewModel = hiltViewModel<ProcedureViewModel>()
            val procedures = viewModel.procedureLiveData.observeAsState(emptyList())

            var showBottomSheet by remember { mutableStateOf(false) }
            var clickedItemId by remember { mutableStateOf("") }

            LaunchedEffect(key1 = Unit) {
                viewModel.getProcedures()
            }

            LaunchedEffect(key1 = clickedItemId) {
                if (clickedItemId.isNotEmpty()) {
                    viewModel.getProcedureDetails(clickedItemId)
                }
            }

            LazyColumn(modifier = Modifier.padding(Spacings.s)) {
                items(procedures.value) { item ->
                    ProcedureListItem(procedure = item, onItemClick = {
                        clickedItemId = it
                        showBottomSheet = true
                    })
                }
            }

            if (showBottomSheet) {
                BottomSheet(onDismissRequest = { showBottomSheet = false })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DigitalSurgeryTheme {
        HomeScreen()
    }
}