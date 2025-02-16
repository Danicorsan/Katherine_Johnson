package app.features.categorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var state by mutableStateOf(CategoryListState())
        private set

    init {
        start()
    }

    /**
     * Start
     *
     */
    private fun start() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(1000)
            state = state.copy(categories = CategoryRepository.getAllCategories(), isLoading = false)
        }
    }

    /**
     * Request delete category
     *
     * @param id
     */
    fun requestDeleteCategory(id: Int) {
        state = state.copy(categoryToDelete = id, isDeleteDialogVisible = true)
    }

    /**
     * Confirm delete category
     *
     */
    fun confirmDeleteCategory() {
        state.categoryToDelete?.let { id ->
            repository.deleteCategory(id)
            state = state.copy(
                isCategoryDeleted = true,
                isDeleteDialogVisible = false,
                categoryToDelete = null
            )
        }
    }

    /**
     * Cancel delete category
     *
     */
    fun cancelDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = false, categoryToDelete = null)
    }
}

