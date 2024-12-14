package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.features.categorycreation.ui.creation.CategoryCreationScreen
import app.features.categorycreation.ui.creation.CategoryCreationViewModel
import app.features.categorylist.ui.CategoryListScreen
import com.example.inventory.home.HomeScreen
import com.example.inventory.navigation.AppNavHost
import com.example.inventory.navigation.CategoryGraph
import com.example.inventory.navigation.CategoryViewModels
import com.example.inventory.navigation.ViewModels
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
                        startDestination = CategoryGraph.ROUTE,
                        viewModels = viewModel
                    )
                }
            }
        }
    }
}