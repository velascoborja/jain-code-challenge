package com.akansha.digitalsurgery.screens.home.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.akansha.digitalsurgery.Constants.APP_NAME
import com.akansha.digitalsurgery.Constants.FAVOURITES_MENU_OPTION
import com.akansha.digitalsurgery.Constants.HOME_MENU_OPTION
import com.akansha.digitalsurgery.Constants.MORE_OPTIONS
import com.akansha.digitalsurgery.screens.home.model.DigitalSurgeryScreen
import com.akansha.digitalsurgery.screens.home.model.MenuState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureAppBar(navController: NavController, menuState: MenuState) {

    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = APP_NAME) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = MORE_OPTIONS
                )
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                if (menuState.showHomeOption) {
                    DropdownMenuItem(text = { Text(HOME_MENU_OPTION) }, onClick = {
                        expanded = false
                        navController.navigate(DigitalSurgeryScreen.Home.name)
                    })
                }
                if (menuState.showFavouritesOption) {
                    DropdownMenuItem(text = { Text(FAVOURITES_MENU_OPTION) }, onClick = {
                        expanded = false
                        navController.navigate(DigitalSurgeryScreen.Favourites.name)
                    })
                }
            }
        },
    )
}
