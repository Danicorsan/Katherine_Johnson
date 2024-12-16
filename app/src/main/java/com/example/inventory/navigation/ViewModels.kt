package com.example.inventory.navigation

import app.features.categorycreation.ui.creation.CategoryCreationViewModel
import app.features.categorycreation.ui.edition.CategoryEditionViewModel
import app.features.categorylist.ui.CategoryListViewModel
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productlist.ui.ProductListViewModel

data class ViewModels(
    val categoryViewModels: CategoryViewModels = CategoryViewModels(),
    val productViewModels : ProductViewModels = ProductViewModels()
)

data class CategoryViewModels(
    val categoryListViewModel: CategoryListViewModel = CategoryListViewModel(),
    val categoryCreationViewModel: CategoryCreationViewModel = CategoryCreationViewModel(),
    val categoryEditionViewModel: CategoryEditionViewModel = CategoryEditionViewModel()
)

data class ProductViewModels(
    val productListViewModel : ProductListViewModel = ProductListViewModel(),
    val productCreationViewModel : ProductCreationViewModel = ProductCreationViewModel(),
    val productEditionViewModel : ProductEditionViewModel = ProductEditionViewModel()
)