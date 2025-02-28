package app.features.productlist.ui.base

import app.domain.invoicing.product.Product
import app.features.productlist.ui.ProductListViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Un data class que almacena todos los evetos utiliados en la pantalla de
 * [app.features.productlist.ui.ProductListScreen].
 *
 * @property onAddProduct Cuando el usuario quiere crear un nuevo producto.
 * @property onDeleteProduct Cuando el usuario quiere eliminar un producto.
 * @property onConfirmDeleteProduct Cuando el usuario confirma que quiere eliminar el producto.
 * @property onDissmissDeleteProduct Cuando el usuario cancela la eliminación del producto.
 * @property seeProductDetails Cuando el usuario quiere ver los detalles de un producto.
 * @property onGoBack Cuando el usuario quiere volver a la pantalla anterior.
 * @property onOpenDrawer Cuando el usuario quiere abrir el ménu lateral.
 * @constructor Create empty Product list events
 */
data class ProductListEvents(
    val onAddProduct : () -> Unit = {},
    val onDeleteProduct : (Product) -> Unit = {},
    val onConfirmDeleteProduct : () -> Unit = {},
    val onDissmissDeleteProduct : () -> Unit = {},
    val seeProductDetails : (Product) -> Unit = {},
    val onGoBack : () -> Unit = {},
    val onOpenDrawer : (CoroutineScope) -> Unit = {},
    val onNavigateProducts: () -> Unit = {},
    val onNavigateCategories: () -> Unit = {},
    val onNavigateInventory: () -> Unit = {}
    ){
    constructor(
        viewModel: ProductListViewModel,
        navigationEvents: ProductListNavigationEvents
    ) : this(
        onAddProduct = viewModel::onCreateProduct,
        onDeleteProduct = viewModel::onDeleteProduct,
        onConfirmDeleteProduct = viewModel::onConfirmDeleteProduct,
        onDissmissDeleteProduct = viewModel::onDissmissDeleteProduct,
        seeProductDetails = viewModel::onSeeProductDetails,
        onGoBack = viewModel::onGoBack,
        onOpenDrawer = viewModel::onOpenDrawer,
        onNavigateProducts = navigationEvents.onNavigateProducts,
        onNavigateCategories = navigationEvents.onNavigateCategories,
        onNavigateInventory = navigationEvents.onNavigateInventory

    )
}

/**
 * Guarda en un data class los eventos o acciones de navegacion
 * que se requieran en [app.features.productlist.ui.ProductListScreen].
 *
 * @property onCreateProductNav Navegación a la pantalla de creación de productos.
 * @property onSeeProductDetailsNav Navegación a la pantalla de detalles de un producto.
 * @property onGoBackNav Navegación a la pantalla anterior.
 * @property onNavigateProducts Navegación a [app.features.productlist.ui.ProductListScreen].
 * @property onNavigateCategories Navegación a [app.features.categorylist.ui.CategoryListScreen].
 * @property onNavigateInventory Navegación a [app.features.inventorylist.ui.InventoryListScreen]
 */
data class ProductListNavigationEvents(
    val onCreateProductNav: () -> Unit = {},
    val onSeeProductDetailsNav : (Int) -> Unit = {},
    val onGoBackNav : () -> Unit = {},
    val onNavigateProducts: () -> Unit = {},
    val onNavigateCategories: () -> Unit = {},
    val onNavigateInventory: () -> Unit = {},
)