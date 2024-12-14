package app.features.categorylist.ui

import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository
import java.util.Date

data class CategoryListState(
    var categories: List<Category> = emptyList(),
    )