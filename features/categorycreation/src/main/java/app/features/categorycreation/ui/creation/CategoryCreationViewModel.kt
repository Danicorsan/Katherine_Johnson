package app.features.categorycreation.ui.creation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CategoryCreationViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var state by mutableStateOf(CategoryCreationState())
        private set

    /**
     * Create category
     */
    fun createCategory() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            if (validateFields()) {
                val newCategory = Category(
                    name = state.name,
                    shortName = state.shortName,
                    description = state.description,
                    image = state.image,
                    createdAt = Date(),
                    typeCategory = state.typeCategory,
                    fungible = state.fungible
                )
                repository.addCategory(newCategory)
                state = CategoryCreationState()
                println("Categoría creada: $newCategory")
            } else {
                state = state.copy(isError = true, showDialog = true)
            }
            state = state.copy(isLoading = false)
        }
    }

    /**
     * Validate fields
     */
    private suspend fun validateFields(): Boolean {
        var hasError = false

        val categories = repository.getAllCategories().first() // Recoge los datos antes de validar

        if (state.name.isEmpty() || categories.any { it.name == state.name }) {
            state = state.copy(isNameError = true)
            hasError = true
        }

        if (state.description.isEmpty()) {
            state = state.copy(isDescriptionError = true)
            hasError = true
        }

        val shortNameRegex = Regex("^[a-zA-Z0-9]{3,}$")
        if (!shortNameRegex.matches(state.shortName) || categories.any { it.shortName == state.shortName }) {
            state = state.copy(isShortNameError = true)
            hasError = true
        }

        return !hasError
    }

    /**
     * On name change
     */
    fun onNameChange(newName: String) {
        state = state.copy(isNameError = false, isError = false, name = newName)

        viewModelScope.launch {
            val categories = repository.getAllCategories().first()
            if (categories.any { it.name == newName }) {
                state = state.copy(isNameError = true)
            }
        }
    }

    /**
     * On description change
     */
    fun onDescriptionChange(newDescription: String) {
        state = state.copy(isDescriptionError = false, isError = false, description = newDescription)
    }

    /**
     * On short name change
     */
    fun onShortNameChange(shortName: String) {
        state = state.copy(isShortNameError = false, isError = false, shortNameErrorMessage = null, shortName = shortName)

        viewModelScope.launch {
            val categories = repository.getAllCategories().first()
            val shortNameRegex = Regex("^[a-zA-Z0-9]{3,}$")

            when {
                !shortNameRegex.matches(shortName) -> {
                    state = state.copy(isShortNameError = true, shortNameErrorMessage = "Debe tener al menos 3 caracteres y solo letras o números.")
                }
                categories.any { it.shortName == shortName } -> {
                    state = state.copy(isShortNameError = true, shortNameErrorMessage = "Este alias ya está en uso.")
                }
            }
        }
    }

    /**
     * On image change
     */
    fun onImageChange(image: Uri) {
        state = state.copy(image = image)
    }

    /**
     * On type category change
     */
    fun onTypeCategoryChange(typeCategory: TypeCategory) {
        state = state.copy(typeCategory = typeCategory)
    }

    /**
     * On discard changes
     */
    fun onDiscardChanges() {
        state = CategoryCreationState()
    }

    /**
     * On fungible change
     */
    fun onFungibleChange(fungible: Boolean) {
        state = state.copy(fungible = fungible)
    }

    /**
     * Dismiss dialog
     */
    fun dismissDialog() {
        state = state.copy(showDialog = false)
    }
}
