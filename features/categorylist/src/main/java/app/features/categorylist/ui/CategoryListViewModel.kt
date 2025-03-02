package app.features.categorylist.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val repository: CategoryRepository,
) : ViewModel() {

    var state by mutableStateOf(CategoryListState())
        private set

    init {
        start()
    }

    /**
     * Inicia la carga de categorías y observa cambios en tiempo real.
     */
    private fun start() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getAllCategories().collect { categorias ->
                categorias.map { c ->
                    Log.d("Category", "Image URI: ${c.image}")
                }
                state = state.copy(categories = categorias, isLoading = false)
            }
        }
    }

    /**
     * Muestra el cuadro de diálogo para confirmar eliminación.
     */
    fun requestDeleteCategory(id: Int) {
        state = state.copy(categoryToDelete = id, isDeleteDialogVisible = true)
    }

    /**
     * Confirma la eliminación de la categoría.
     */
    fun confirmDeleteCategory() {
        viewModelScope.launch {
            state.categoryToDelete?.let { id ->
                repository.deleteCategory(repository.getCategoryById(id).first())
            }
            state = state.copy(
                isCategoryDeleted = true,
                isDeleteDialogVisible = false,
                categoryToDelete = null
            )
        }
    }


    /**
     * Cancela la eliminación de la categoría.
     */
    fun cancelDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = false, categoryToDelete = null)
    }
}

