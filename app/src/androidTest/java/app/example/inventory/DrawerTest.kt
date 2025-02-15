package com.example.inventory

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.google.common.truth.Truth
import com.example.inventory.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.inventory.R.string as MainStrings
/*
@HiltAndroidTest
class DrawerTest {
    private lateinit var estimatesDrawerItem: String
    private lateinit var customersDrawerItem: String
    private lateinit var itemsDrawerItem: String
    private lateinit var invoicesDrawerItem: String
    private lateinit var settingsDrawerItem: String

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeScreen(navController = navController)
            navController.navigate(InvoicesGraph.List.syntax())
        }

        invoicesDrawerItem = composeTestRule.getString(MainStrings.menu_invoices)
        itemsDrawerItem = composeTestRule.getString(MainStrings.menu_items)
        customersDrawerItem = composeTestRule.getString(MainStrings.menu_customers)
        estimatesDrawerItem = composeTestRule.getString(MainStrings.menu_estimates)
        settingsDrawerItem = composeTestRule.getString(MainStrings.menu_settings)
    }

    private fun AndroidComposeTestRule<*, *>.getString(id: Int) = activity.getString(id)

    @Test
    fun testPrincipalNavigationDestinations() {

        swipeDrawerAndTouch(itemsDrawerItem)
        navController.assertCurrentRoute(ItemsGraph.List.syntax())

        swipeDrawerAndTouch(customersDrawerItem)
        navController.assertCurrentRoute(CustomersGraph.List.syntax())

        swipeDrawerAndTouch(estimatesDrawerItem)
        navController.assertCurrentRoute(EstimatesGraph.List.syntax())

        swipeDrawerAndTouch(invoicesDrawerItem)
        navController.assertCurrentRoute(InvoicesGraph.List.syntax())

        swipeDrawerAndTouch(settingsDrawerItem)
        navController.assertCurrentRoute(SettingsGraph.List.syntax())
    }

    private fun swipeDrawerAndTouch(anItem: String) {
        composeTestRule.onNodeWithContentDescription("Navigation Drawer")
            .performTouchInput { swipeRight() }
        composeTestRule.onNode(hasContentDescription("Drawer item") and hasText(anItem))
            .performClick()
    }

    private fun NavHostController.assertCurrentRoute(destination: String) {
        val route = currentBackStackEntry?.destination?.route
        Truth.assertThat(route).isEqualTo(destination)
    }
}

 */