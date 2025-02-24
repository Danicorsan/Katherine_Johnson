package com.example.inventory.navigation.graphs

object InventoryGraph {
    const val ROUTE = "inventory"

    fun inventoryListRoute() = "$ROUTE/inventoryList"
    fun inventoryCreationRoute() = "$ROUTE/inventoryCreation"
    fun inventoryEditionRoute(inventoryId: String) = "$ROUTE/inventoryEdition/$inventoryId"
    fun inventoryDetailsRoute(inventoryId: String) = "$ROUTE/inventoryDetails/$inventoryId"
}