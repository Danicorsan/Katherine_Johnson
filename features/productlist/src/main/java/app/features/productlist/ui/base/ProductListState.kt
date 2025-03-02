package app.features.productlist.ui.base

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import app.domain.invoicing.product.Product

/**
 * Un data class que alamcena el estado de la vista
 * [app.features.productlist.ui.ProductListScreen]
 *
 * @property productList La lista de [Product] a mostrar.
 * @property productIsBeingDeleted Indica si hay un producto siendo eliminado.
 * @property isLoading Indica si la pantalla está cargando.
 * @property drawerState Un objeto [DrawerState] que controla el estado del
 * [app.base.ui.composables.AppDrawer] en la pantalla [app.features.productlist.ui.ProductListScreen]
 */
data class ProductListState(
    val productList : List<Product> = emptyList(),
    val productIsBeingDeleted : Boolean = false,
    val isLoading : Boolean = false,
    val drawerState : DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    val ordenationState: OrdenationState = OrdenationState.DESCENDING
)

enum class OrdenationState{
    DESCENDING, ASCENDING;
}