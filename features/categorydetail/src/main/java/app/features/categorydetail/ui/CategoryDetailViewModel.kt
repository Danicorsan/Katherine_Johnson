package app.features.categorydetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Category detail view model
 *
 * @property repository
 * @constructor Create empty Category detail view model
 */
@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val repository: CategoryRepository,
) : ViewModel() {

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
            val categoria = repository.getCategoryById(id).firstOrNull()

            state = if (categoria != null) {
                state.copy(category = categoria, notFoundError = false, isLoading = false)
            } else {
                state.copy(notFoundError = true, isLoading = false)
            }
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
        viewModelScope.launch {
            state.category?.let { category ->

                repository.deleteCategory(category)
            }
            state = state.copy(isDeleteDialogVisible = false)
            onGoBack() // Navegación después de la eliminación
        }
    }


    /**
     * Cancel delete category
     *
     */
    fun cancelDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = false)
    }
}
