package app.features.categorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CategoryCreationViewModel : ViewModel() {

    var state by mutableStateOf(CategoryCreationState())

    fun createCategory(name: String, description: String) {
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