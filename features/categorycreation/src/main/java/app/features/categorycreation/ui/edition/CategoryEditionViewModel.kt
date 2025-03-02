package app.features.categorycreation.ui.edition

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryEditionViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    var state by mutableStateOf(CategoryEditionState())
        private set

    /**
     * Load category
     */
    fun loadCategory(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val category = repository.getCategoryById(id).firstOrNull()

            state = if (category != null) {
                CategoryEditionState(
                    category = category,
                    name = category.name,
                    shortName = category.shortName,
                    description = category.description,
                    typeCategory = category.typeCategory,
                    fungible = category.fungible,
                    image = category.image
                )
            } else {
                state.copy(notFoundError = true)
            }
            state = state.copy(isLoading = false)
        }
    }

    /**
     * On event
     */
    fun onEvent(event: CategoryEditionEvent) {
        when (event) {
            is CategoryEditionEvent.OnNameChange -> {
                state = state.copy(name = event.name, isNameError = false)
                validateName(event.name)
            }
            is CategoryEditionEvent.OnShortNameChange -> {
                state = state.copy(shortName = event.shortName, isShortNameError = false)
                validateShortName(event.shortName)
            }
            is CategoryEditionEvent.OnDescriptionChange -> {
                state = state.copy(description = event.description, isDescriptionError = event.description.isEmpty())
            }
            is CategoryEditionEvent.OnTypeCategoryChange -> {
                state = state.copy(typeCategory = event.typeCategory)
            }
            is CategoryEditionEvent.ConfirmChanges -> confirmChanges()
        }
    }

    /**
     * Confirm changes
     */
    private fun confirmChanges() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            if (validateFields()) {
                state.category?.copy(
                    name = state.name,
                    shortName = state.shortName,
                    description = state.description,
                    typeCategory = state.typeCategory,
                    fungible = state.fungible,
                    image = state.image
                )?.let {
                    repository.updateCategory(it)
                    state = state.copy(isError = false)
                }
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
        val categories = repository.getAllCategories().first()

        val isNameDuplicate = categories.any { it.id != state.category?.id && it.name == state.name }
        val isShortNameDuplicate = categories.any { it.id != state.category?.id && it.shortName == state.shortName }

        val hasError = state.name.isEmpty() || isNameDuplicate ||
                state.shortName.isEmpty() || isShortNameDuplicate ||
                state.description.isEmpty()

        state = state.copy(
            isNameError = state.name.isEmpty() || isNameDuplicate,
            isShortNameError = state.shortName.isEmpty() || isShortNameDuplicate,
            isDescriptionError = state.description.isEmpty(),
            isError = hasError
        )

        return !hasError
    }

    /**
     * Validate Name
     */
    private fun validateName(name: String) {
        viewModelScope.launch {
            val categories = repository.getAllCategories().first()
            if (categories.any { it.id != state.category?.id && it.name == name }) {
                state = state.copy(isNameError = true)
            }
        }
    }

    /**
     * Validate Short Name
     */
    private fun validateShortName(shortName: String) {
        viewModelScope.launch {
            val categories = repository.getAllCategories().first()
            if (categories.any { it.id != state.category?.id && it.shortName == shortName }) {
                state = state.copy(isShortNameError = true)
            }
        }
    }

    fun onFungibleChange(fungible: Boolean) {
        state = state.copy(fungible = fungible)
    }

    fun onImageChange(image: Uri) {
        state = state.copy(image = image)
    }
}
