package app.features.categorydetail.ui

import app.domain.invoicing.category.Category

data class CategoryDetailState(
    val category: Category? = null,
    val notFoundError: Boolean = false,
    val isDeleteDialogVisible: Boolean = false
)