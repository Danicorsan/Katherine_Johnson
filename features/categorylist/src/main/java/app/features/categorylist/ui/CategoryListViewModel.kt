package app.features.categorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.domain.invoicing.repository.CategoryRepository

class CategoryListViewModel : ViewModel() {

    var state by mutableStateOf(CategoryListState())
        private set

    init {
        state = state.copy(categories = CategoryRepository.getAllCategories())
    }

    fun newCategory(){

    }

}