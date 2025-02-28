package app.example.inventory.navigation.graphs

import androidx.navigation.NavController

/**
 * Permite normalizar la navegación entre pantallas a traves del Drawer.
 *
 * Este método permite navegar evitando el apilamiento
 * de la pila de navegaciónes.
 *
 * @param navController El [NavController] de la aplicación.
 * @param route La ruta a la que se quiere navegar.
 */
internal fun drawerNavigation(navController: NavController, route : String){
    navController.navigate(route){
        popUpTo(0){ inclusive = true }
        launchSingleTop = true
    }
}