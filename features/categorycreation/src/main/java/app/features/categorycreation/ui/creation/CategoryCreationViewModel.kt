package app.features.categorycreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import app.domain.invoicing.repository.CategoryRepository
import app.features.categorycreation.ui.base.CategoryCreationState
import java.util.Date

class CategoryCreationViewModel(private val repository: CategoryRepository = CategoryRepository) :
    ViewModel() {

    var state by mutableStateOf(CategoryCreationState())
        private set

    fun createCategory() {
        if (validateFields()) {
            val newCategory = Category(
                id = repository.getAllCategories().size + 1,
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
            state = state.copy(isError = false)
            println("Categoría creada: $newCategory")
        } else {
            state = state.copy(isError = true)
        }
    }

    private fun validateFields(): Boolean {
        var hasError = false

        if (state.name.isEmpty() || CategoryRepository.getAllCategories()
                .any { it.name == state.name }
        ) {
            state = state.copy(isNameError = true)
            hasError = true
        }

        if (state.description.isEmpty()) {
            state = state.copy(isDescriptionError = true)
            hasError = true
        }

        val shortNameRegex = Regex("^[a-zA-Z0-9]{3,}\$")
        if (!shortNameRegex.matches(state.shortName) || CategoryRepository.getAllCategories()
                .any { it.shortName == state.shortName }
        ) {
            state = state.copy(isShortNameError = true)
            hasError = true
        }

        state = state.copy(isError = hasError)
        return !hasError
    }

    fun onNameChange(newName: String) {
        state = state.copy(isNameError = false, isError = false)

        if (newName == CategoryRepository.getAllCategories().find { it.name == newName }?.name) {
            state = state.copy(
                isNameError = true,
            )
        }

        state = state.copy(name = newName)
    }

    fun onDescriptionChange(newDescription: String) {
        state = state.copy(isDescriptionError = false, isError = false)

        if (newDescription.isEmpty()) {
            state = state.copy(
                isDescriptionError = true,
            )
        }

        state = state.copy(description = newDescription)
    }

    fun onShortNameChange(shortName: String) {
        state = state.copy(isShortNameError = false, isError = false, shortNameErrorMessage = null)

        val shortNameRegex = Regex("^[a-zA-Z0-9]{3,}\$")

        when {
            !shortNameRegex.matches(shortName) -> {
                state = state.copy(
                    isShortNameError = true,
                    shortNameErrorMessage = "1"
                )
            }

            CategoryRepository.getAllCategories().any { it.shortName == shortName } -> {
                state = state.copy(
                    isShortNameError = true,
                    shortNameErrorMessage = "2"
                )
            }
        }

        state = state.copy(shortName = shortName)
    }

    fun onImageChange(image: String) {
        state = state.copy(image = image)
    }

    fun onTypeCategoryChange(typeCategory: TypeCategory) {
        state = state.copy(typeCategory = typeCategory)
    }

    fun onDiscardChanges() {
        state = CategoryCreationState()
    }

    fun onFungibleChange(fungible: Boolean) {
        state = state.copy(fungible = fungible)
    }
}
