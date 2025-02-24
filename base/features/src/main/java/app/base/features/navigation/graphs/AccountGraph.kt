package com.example.inventory.navigation.graphs

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

