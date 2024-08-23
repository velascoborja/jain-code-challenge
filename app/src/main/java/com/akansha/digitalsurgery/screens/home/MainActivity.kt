package com.akansha.digitalsurgery.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.akansha.digitalsurgery.screens.home.design.DigitalSurgeryApp
import com.akansha.digitalsurgery.ui.theme.DigitalSurgeryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalSurgeryTheme { DigitalSurgeryApp() }
        }
    }
}
