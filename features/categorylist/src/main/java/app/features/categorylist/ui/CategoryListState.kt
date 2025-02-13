package app.features.categorylist.ui

import app.domain.invoicing.category.Category

data class CategoryListState(
    var categories: List<Category> = emptyList(),
    var isLoading: Boolean = false,
    var isDeleteDialogVisible: Boolean = false,
    var isCategoryDeleted: Boolean = false,
    var categoryToDelete: Int? = null // Nueva propiedad para almacenar la categoría a eliminar
)
