package com.akansha.digitalsurgery.screens.home.design

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akansha.digitalsurgery.model.Result
import com.akansha.digitalsurgery.screens.home.ProcedureViewModel
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme

@Composable
fun HomeScreen(modifier: Modifier, viewModel: ProcedureViewModel = viewModel()) {

    val procedures = viewModel.procedureLiveData.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getProcedures()
    }

    if (procedures.value is Result.Success) {
        Log.d("Procedures", (procedures.value as Result.Success).procedures.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DigitalSurgeryTheme {
        HomeScreen(modifier = Modifier)
    }
}