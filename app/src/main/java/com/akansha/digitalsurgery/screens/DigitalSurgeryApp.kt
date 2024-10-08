package com.akansha.digitalsurgery.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akansha.digitalsurgery.Constants.DEFAULT_STRING
import com.akansha.digitalsurgery.Constants.FAVOURITE_STATE_ADDED
import com.akansha.digitalsurgery.Constants.FAVOURITE_STATE_REMOVED
import com.akansha.digitalsurgery.screens.favourites.FavouritesScreen
import com.akansha.digitalsurgery.screens.common.model.DigitalSurgeryScreen
import com.akansha.digitalsurgery.screens.common.model.FavouriteState
import com.akansha.digitalsurgery.screens.common.model.MenuState
import com.akansha.digitalsurgery.screens.home.HomeScreen
import com.akansha.digitalsurgery.screens.common.design.ProcedureAppBar
import kotlinx.coroutines.launch


@Composable
fun DigitalSurgeryApp() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var menuState: MenuState by remember {
        mutableStateOf(MenuState(showHomeOption = false, showFavouritesOption = true))
    }
    var showSnackBar: Boolean by remember { mutableStateOf(false) }
    var favouriteState: FavouriteState? by remember { mutableStateOf(null) }
    var procedureName: String? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = showSnackBar) {
        if (showSnackBar) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = when (favouriteState) {
                        FavouriteState.ADDED -> "$procedureName $FAVOURITE_STATE_ADDED"
                        FavouriteState.REMOVED -> "$procedureName $FAVOURITE_STATE_REMOVED"
                        else -> DEFAULT_STRING
                    },
                    duration = SnackbarDuration.Short,
                    withDismissAction = true,
                )
            }
            showSnackBar = false
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { ProcedureAppBar(navController, menuState) },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DigitalSurgeryScreen.HOME.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = DigitalSurgeryScreen.HOME.name) {
                menuState = MenuState(showHomeOption = false, showFavouritesOption = true)
                HomeScreen(showSnackBar = { name, state ->
                    showSnackBar = true
                    procedureName = name
                    favouriteState = state
                })
            }

            composable(route = DigitalSurgeryScreen.FAVOURITES.name) {
                menuState = MenuState(showHomeOption = true, showFavouritesOption = false)
                FavouritesScreen(showSnackBar = { name, state ->
                    showSnackBar = true
                    procedureName = name
                    favouriteState = state
                })
            }
        }
    }
}
