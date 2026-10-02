package com.aeroscape.wallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aeroscape.wallpapers.ui.navigation.AppNavigation
import com.aeroscape.wallpapers.ui.theme.AeroscapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AeroscapeTheme {
                AppNavigation()
            }
        }
    }
}
