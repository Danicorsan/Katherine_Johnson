package app.features.categorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    var state by mutableStateOf(CategoryListState())
        private set

    init {
        start()
    }

    private fun start() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(5000)
            state = state.copy(categories = CategoryRepository.getAllCategories())
            state = state.copy(isLoading = false)
        }
    }

}