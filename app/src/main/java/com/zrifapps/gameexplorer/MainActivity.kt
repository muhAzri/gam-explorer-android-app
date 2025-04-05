package com.zrifapps.gameexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.zrifapps.gameexplorer.navigation.AppRouter
import com.zrifapps.gameexplorer.navigation.GameList
import com.zrifapps.gameexplorer.ui.theme.GameExplorerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameExplorerTheme {
                AppRouter(
                    navController = rememberNavController(),
                    startDestination = GameList,
                )
            }
        }
    }
}
