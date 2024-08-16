package com.akansha.digitalsurgery.screens.home.design

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akansha.digitalsurgery.Constants.APP_NAME
import com.akansha.digitalsurgery.model.Result
import com.akansha.digitalsurgery.screens.home.ProcedureViewModel
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ProcedureViewModel = viewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(text = APP_NAME) }) }
    ) { innerPadding ->
        val procedures = viewModel.procedureLiveData.observeAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.getProcedures()
        }

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            if (procedures.value is Result.Success) {
                items((procedures.value as Result.Success).procedures) {
                    ProcedureListItem(it)
                }
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