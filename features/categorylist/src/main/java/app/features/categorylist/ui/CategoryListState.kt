package app.features.categorylist.ui

import app.domain.invoicing.category.Category

data class CategoryListState(
    var categories: List<Category> = emptyList(),
    var isLoading:Boolean = false
)