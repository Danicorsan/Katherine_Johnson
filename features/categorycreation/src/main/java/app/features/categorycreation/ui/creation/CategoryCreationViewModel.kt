package app.features.categorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.base.ui.composables.BaseAlertDialog
import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository
import app.features.categorycreation.ui.base.CategoryCreationState
import java.util.Date

class CategoryCreationViewModel(private val repository: CategoryRepository = CategoryRepository) : ViewModel() {

    var state by mutableStateOf(CategoryCreationState())
        private set

    fun createCategory(name: String, description: String,) {

        if (!state.isNameError && !state.isDescriptionError && !state.isShortNameError) {
            val newCategory = Category(
                id = repository.getAllCategories().size + 1,
                name = state.name,
                shortName = state.shortName,
                description = state.description,
                image = state.image,
                createdAt = Date()
            )
            repository.addCategory(newCategory)
            state = CategoryCreationState()
            println("Categoría creada: ${newCategory}")
        }else {
            state = state.copy(isError = true)
        }

    }

    fun onNameChange(newName: String) {
        state = state.copy(isNameError = false)

        if (newName.isEmpty()) {
            state = state.copy(
                isNameError = true,
            )
        }

        // Actualiza el nombre en el estado
        state = state.copy( name = newName)
    }

    fun onDescriptionChange(newDescription: String) {
        state = state.copy(isDescriptionError = false)

        if (newDescription.isEmpty()) {
            state = state.copy(
                isDescriptionError = true,
            )
        }
        // Actualiza la descripción en el estado
        state = state.copy( description = newDescription)
    }

    fun onShortNameChange(shortName: String) {
        state = state.copy(isShortNameError = false)

        if (shortName.isEmpty()) {
            state = state.copy(
                isShortNameError = true,
            )
        }

        state = state.copy( shortName = shortName)
    }

    fun onImageChange(image: String) {
        state = state.copy( image = image)
    }

}