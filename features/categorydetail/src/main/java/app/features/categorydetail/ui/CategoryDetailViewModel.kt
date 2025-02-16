package app.features.categorydetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(CategoryDetailState())
        private set

    /**
     * Load category
     *
     * @param id
     */
    fun loadCategory(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(1000)
            val categoria: Category? = CategoryRepository.getCategoryById(id)
            state = if (categoria != null) {
                state.copy(category = categoria, notFoundError = false)
            } else {
                state.copy(notFoundError = true)
            }
            state = state.copy(isLoading = false)
        }

    }

    /**
     * Request delete category
     *
     */
    fun requestDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = true)
    }

    /**
     * Confirm delete category
     *
     * @param onGoBack
     * @receiver
     */
    fun confirmDeleteCategory(onGoBack: () -> Unit) {
        state.category?.let {
            CategoryRepository.deleteCategory(it.id)
        }
        state = state.copy(isDeleteDialogVisible = false)
        onGoBack()
    }

    /**
     * Cancel delete category
     *
     */
    fun cancelDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = false)
    }
}