package app.features.categorylist.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import app.domain.invoicing.category.Category
import app.features.productlist.ui.base.OrdenationState

/**
 * Category list state
 *
 * @property categories
 * @property isLoading
 * @property isDeleteDialogVisible
 * @property isCategoryDeleted
 * @property categoryToDelete
 * @constructor Create empty Category list state
 */
data class CategoryListState(
    var categories: List<Category> = emptyList(),
    var isLoading: Boolean = false,
    var isDeleteDialogVisible: Boolean = false,
    var isCategoryDeleted: Boolean = false,
    var categoryToDelete: Int? = null, // Nueva propiedad para almacenar la categoría a eliminar
    val drawerState : DrawerState = DrawerState(initialValue = DrawerValue.Closed),
    val ordenationState: OrdenationState = OrdenationState.DESCENDING
)
