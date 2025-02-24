package app.base.ui.components.basenavigationdrawer

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import app.base.ui.R
import com.example.inventory.navigation.graphs.CategoryGraph
import com.example.inventory.navigation.graphs.InventoryGraph
import com.example.inventory.navigation.graphs.ProductGraph

internal sealed class BaseNavigationDrawerItem(
    val label : String,
    val icon : ImageVector,
    val contentDescription : String,
    val onClick : () -> Unit
){
    companion object{
        /**
         * Se desea que en cada navegación se elimine por completo la pila de navegación
         * y que solo quede el destino.
         *
         * @param navController El elemento [NavController] de la aplicación
         * @param route La ruta a navegar
         */
        private fun navigate(navController: NavController, route : String){
            navController.navigate(route){
                popUpTo(0){ inclusive = true }
                launchSingleTop = true
            }
        }

        /**
         * Permite obtener una lista de objetos [BaseNavigationDrawerItem] correspondientes
         * a las pantallas principales de la aplicación, como productos, categorias e inventarios.
         *
         * @param navController El objeto [NavController] de la aplicación.
         * @return La lista correspondiente.
         */
        @Composable
        internal fun getMainScreensItems(navController: NavController) : List<BaseNavigationDrawerItem>{
            val context = LocalContext.current
            return listOf(
                ProductItemBase(context, navController),
                CategoryItemBase(context, navController, InventoryIcon()),
                InventoryItemBase(context, navController, CategoryIcon())
            )
        }

        /**
         * Permite obtener una lista de objetos [BaseNavigationDrawerItem] correspondientes
         * a las pantallas "aparatadas" o "secundarias" que no entran del objetivo
         * de la aplicación como serian los ajustes o la pantalla de "Acerca de nosotros".
         *
         * @param navController El objeto [NavController] de la aplicación
         * @return La lista correspondiente
         */
        @Composable
        internal fun getOtherScreensItems(navController: NavController) : List<BaseNavigationDrawerItem>{
            val context = LocalContext.current
            return listOf(
                SettingsItemBase(context, navController),
                AboutUs(context, navController)
            )
        }
    }

    abstract fun getAsociatedNavigationRoute() : String

    private class ProductItemBase(context: Context, navController: NavController) : BaseNavigationDrawerItem(
        label = context.getString(R.string.drawer_product_item_label),
        icon = Icons.Default.ShoppingCart,
        contentDescription = context.getString(R.string.drawer_product_item_content_description),
        onClick = {
            navigate(navController, ProductGraph.ROUTE)
        }
    ) {
        override fun getAsociatedNavigationRoute() = ProductGraph.ROUTE
    }

    private class CategoryItemBase(context: Context, navController: NavController, categoryIcon : ImageVector) : BaseNavigationDrawerItem(
        label = context.getString(R.string.drawer_category_item_label),
        icon = categoryIcon,
        contentDescription = context.getString(R.string.drawer_category_item_content_description),
        onClick = {
            navigate(navController, CategoryGraph.ROUTE)
        }
    ){
        override fun getAsociatedNavigationRoute(): String = CategoryGraph.ROUTE
    }

    private class InventoryItemBase(context: Context, navController: NavController, inventoryIcon : ImageVector) : BaseNavigationDrawerItem(
        label = context.getString(R.string.drawer_inventory_item_label),
        icon = inventoryIcon,
        contentDescription = context.getString(R.string.drawer_inventory_item_content_description),
        onClick = {
            navigate(navController, InventoryGraph.ROUTE)
        }
    ){
        override fun getAsociatedNavigationRoute(): String = InventoryGraph.ROUTE
    }

    private class SettingsItemBase(context: Context, navController: NavController) : BaseNavigationDrawerItem(
        label = context.getString(R.string.drawer_settings_item_label),
        icon = Icons.Default.Build,
        contentDescription = context.getString(R.string.drawer_settings_item_content_description),
        onClick = {
            Toast.makeText(context,
                context.getString(R.string.drawer_settings_item_content_description),
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {
        override fun getAsociatedNavigationRoute(): String = ""
    }

    private class AboutUs(context: Context, navController: NavController) : BaseNavigationDrawerItem(
        label = context.getString(R.string.drawer_aboutus_item_label),
        icon = Icons.Default.Info,
        contentDescription = context.getString(R.string.drawer_aboutus_item_content_description),
        onClick = {
            Toast.makeText(context,
                context.getString(R.string.drawer_aboutus_item_content_description),
                Toast.LENGTH_SHORT
            ).show()
        }
    ){
        override fun getAsociatedNavigationRoute(): String = ""
    }
}
