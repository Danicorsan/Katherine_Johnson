package com.example.inventory.navigation

import app.features.categorycreation.ui.creation.CategoryCreationViewModel
import app.features.categorycreation.ui.edition.CategoryEditionViewModel
import app.features.categorydetail.ui.CategoryDetailViewModel
import app.features.categorylist.ui.CategoryListViewModel

data class ViewModels(
    val categoryViewModels: CategoryViewModels = CategoryViewModels(),
)

data class CategoryViewModels(
    val categoryListViewModel: CategoryListViewModel = CategoryListViewModel(),
    val categoryCreationViewModel: CategoryCreationViewModel = CategoryCreationViewModel(),
    val categoryEditionViewModel: CategoryEditionViewModel = CategoryEditionViewModel(),
    val categoryDetailViewModel: CategoryDetailViewModel = CategoryDetailViewModel()
)