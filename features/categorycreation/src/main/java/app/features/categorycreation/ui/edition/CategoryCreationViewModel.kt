package app.features.categorycreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.features.categorycreation.ui.base.CategoryCreationState

class CategoryEditionViewModel : ViewModel() {

    var state by mutableStateOf(CategoryCreationState())

    fun editCategory(name: String, description: String, shortName: String, image: String?) {
        // Lógica para crear la categoría
        println("Categoría creada: $name, $description")
    }

    fun onNameChange(newName: String) {
        // Actualiza el nombre en el estado
        state = state.copy( name = newName)
    }

    fun onDescriptionChange(newDescription: String) {
        // Actualiza la descripción en el estado
        state = state.copy( description = newDescription)
    }

}