package com.example.inventory.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.accountsignin.ui.LoginScreen
import app.features.accountsignup.ui.SignUpScreen
import com.example.inventory.home.HomeScreen
import com.example.inventory.navigation.graphs.AccountGraph.EMAIL
import com.example.inventory.navigation.graphs.AccountGraph.PASSWORD
import com.example.inventory.navigation.graphs.AccountGraph.ROUTE

/**
 * Account graph
 *
 * @constructor Create empty Account graph
 */
object AccountGraph {
    const val ROUTE = "signUp"
    const val EMAIL = "email"
    const val PASSWORD = "password"

    fun login() = "$ROUTE/login?${EMAIL}={email}&${PASSWORD}={password}"
    fun register() = "register_screen"
}

object MainGraph {
    const val ROUTE = "main"
}

fun NavGraphBuilder.accountGraph(
    navController: NavController
) {
    navigation(startDestination = AccountGraph.login(), route = ROUTE) {
        login(navController)
        signUp(navController)
    }
}

fun NavGraphBuilder.mainGraph(
    navController: NavController
) {
    composable(MainGraph.ROUTE) {
        HomeScreen(
            onNavigateCategories = {
                navController.navigate(CategoryGraph.categoryListRoute())
            },
            onNavigateProducts = {
                navController.navigate(ProductGraph.ROUTE)
            },
            onNavigateInventory = {
                navController.navigate(InventoryGraph.ROUTE)
            }
        )
    }
}


private fun NavGraphBuilder.login(navController: NavController) {
    composable(
        AccountGraph.login(),
        arguments = listOf(
            navArgument(EMAIL) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(PASSWORD) {
                type = NavType.StringType
                defaultValue = ""
            },
        )
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString(EMAIL) ?: ""
        val password = backStackEntry.arguments?.getString(PASSWORD) ?: ""
        LoginScreen(
            email = email,
            password = password,
            onClickCrearCuenta = { navController.navigate(AccountGraph.register()) },
            viewModel = hiltViewModel(),
            onSuccess = {
                navController.navigate(MainGraph.ROUTE) {
                    popUpTo(ROUTE) { inclusive = true } // Borra la pila de login
                }
            },
        )
    }
}


private fun NavGraphBuilder.signUp(navController: NavController) {
    composable(AccountGraph.register()) {
        SignUpScreen(
            viewModel = hiltViewModel(),
            onRegisterSuccess = { email, password ->
                navController.navigate(
                    "$ROUTE/login?$EMAIL=$email&$PASSWORD=$password"
                )
            },
            onNavigateToLogin = { navController.navigate(ROUTE) }
        )
    }
}
