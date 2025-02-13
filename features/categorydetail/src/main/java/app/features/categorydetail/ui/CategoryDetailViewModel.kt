package app.features.categorydetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository

class CategoryDetailViewModel : ViewModel() {
    // Estado que contiene la categoría actual
    var state by mutableStateOf(CategoryDetailState())
        private set

    // Función para cargar los detalles de la categoría
    fun loadCategory(id: Int) {
        val categoria:Category? = CategoryRepository.getCategoryById(id)
        state = if (categoria!=null){
            state.copy(category = categoria)
        }else {
            state.copy(notFoundError = true)
        }
    }

}