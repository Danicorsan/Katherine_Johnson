package app.features.categorydetail.ui

import app.domain.invoicing.category.Category

/**
 * Category detail state
 *
 * @property category
 * @property notFoundError
 * @property isDeleteDialogVisible
 * @property isLoading
 * @constructor Create empty Category detail state
 */
data class CategoryDetailState(
    val category: Category? = null,
    val notFoundError: Boolean = false,
    val isDeleteDialogVisible: Boolean = false,
    val isLoading:Boolean = false
)