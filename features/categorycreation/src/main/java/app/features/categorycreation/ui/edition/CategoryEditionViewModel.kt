package app.features.categorycreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.domain.invoicing.category.TypeCategory
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryEditionViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var state by mutableStateOf(CategoryEditionState())
        private set

    fun loadCategory(id: Int) {
        val category = repository.getCategoryById(id)
        state = if (category != null) {
            CategoryEditionState(category = category)
        } else {
            state.copy(notFoundError = true)
        }
    }

    fun confirmChanges() {
        if (validateFields()) {
            val updatedCategory = state.category?.copy(
                name = state.name,
                shortName = state.shortName,
                description = state.description,
                image = state.image,
                typeCategory = state.typeCategory,
                fungible = state.fungible
            )
            if (updatedCategory != null) {
                repository.updateCategory(updatedCategory)
                println("Categoria supuestamente actualizada: $updatedCategory")
                state = state.copy(isError = false)
            }
        } else {
            state = state.copy(isError = true, showDialog = true)
        }
    }

    private fun validateFields(): Boolean {
        var hasError = false

        // Validación de nombre
        if (state.name.isEmpty() || repository.getAllCategories()
                .any { it.name == state.name && it.id != state.category?.id }
        ) {
            state = state.copy(isNameError = true)
            hasError = true
        }

        // Validación de descripción
        if (state.description.isEmpty()) {
            state = state.copy(isDescriptionError = true)
            hasError = true
        }

        // Validación de nombre corto
        val shortNameRegex = Regex("^[a-zA-Z0-9]{3,}\$") // Validación de nombre corto con regex
        if (!shortNameRegex.matches(state.shortName) || repository.getAllCategories()
                .any { it.shortName == state.shortName && it.id != state.category?.id }
        ) {
            state = state.copy(isShortNameError = true)
            hasError = true
        }

        state = state.copy(isError = hasError)
        return !hasError
    }

    fun onNameChange(newName: String) {
        state = state.copy(name = newName, isNameError = false)
    }

    fun onDescriptionChange(newDescription: String) {
        state = state.copy(description = newDescription, isDescriptionError = false)
    }

    fun onShortNameChange(shortName: String) {
        state = state.copy(shortName = shortName, isShortNameError = false)
    }

    fun onTypeCategoryChange(typeCategory: TypeCategory) {
        state = state.copy(typeCategory = typeCategory)
    }

    fun onFungibleChange(fungible: Boolean) {
        state = state.copy(fungible = fungible)
    }

    fun dismissDialog() {
        state = state.copy(showDialog = false)
    }
}
