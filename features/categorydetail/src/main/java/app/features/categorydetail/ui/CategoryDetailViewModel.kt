package app.features.categorydetail.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import app.domain.invoicing.category.Category

class CategoryDetailViewModel : ViewModel() {
    // Estado que contiene la categoría actual
    var category = mutableStateOf<Category?>(null)
        private set

    // Función para cargar los detalles de la categoría
    fun loadCategory(category: Category) {
        this.category.value = category
    }

    // Función para actualizar la categoría (si se edita)
    fun updateCategory(updatedCategory: Category) {
        this.category.value = updatedCategory
    }
}