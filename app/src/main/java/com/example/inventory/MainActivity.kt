package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.inventory.navigation.AppNavHost
import com.example.inventory.navigation.ViewModels
import com.example.inventory.navigation.graphs.AccountGraph
import com.example.inventory.theme.InventoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel = ViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            InventoryTheme {
                Surface {
                    AppNavHost(
                        navController = navController,
                        startDestination = AccountGraph.ROUTE,
                        viewModels = viewModel
                    )
                }
            }
        }
    }
}