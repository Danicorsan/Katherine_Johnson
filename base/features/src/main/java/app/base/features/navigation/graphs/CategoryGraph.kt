package com.example.inventory.navigation.graphs

/**
 * Category graph
 *
 * @constructor Create empty Category graph
 */
object CategoryGraph {
    const val ID = "id"
    const val ROUTE = "category"

    fun categoryListRoute() = "$ROUTE/categoryList"
    fun categoryCreationRoute() = "$ROUTE/categoryCreation"
    fun categoryEditionRoute(id: Int) = "$ROUTE/categoryEdition/$id"
    fun categoryDetailRoute(id: Int) = "$ROUTE/categoryDetail/$id"
}