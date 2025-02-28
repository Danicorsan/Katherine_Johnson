package com.example.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.inventory.navigation.graphs.AccountGraph
import com.example.inventory.navigation.graphs.accountGraph
import com.example.inventory.navigation.graphs.categoryGraph
import com.example.inventory.navigation.graphs.inventoryGraph
import com.example.inventory.navigation.graphs.productGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = AccountGraph.login(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        categoryGraph(
            navController = navController,
        )
        productGraph(
            navController = navController
        )
        inventoryGraph(
            navController = navController
        )
        accountGraph(
            navController = navController
        )
    }
}
