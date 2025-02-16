package app.features.categorydetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(CategoryDetailState())
        private set

    fun loadCategory(id: Int) {
        val categoria: Category? = CategoryRepository.getCategoryById(id)
        state = if (categoria != null) {
            state.copy(category = categoria, notFoundError = false)
        } else {
            state.copy(notFoundError = true)
        }
    }

    fun requestDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = true)
    }

    fun confirmDeleteCategory(onGoBack: () -> Unit) {
        state.category?.let {
            CategoryRepository.deleteCategory(it.id)
        }
        state = state.copy(isDeleteDialogVisible = false)
        onGoBack()
    }

    fun cancelDeleteCategory() {
        state = state.copy(isDeleteDialogVisible = false)
    }
}