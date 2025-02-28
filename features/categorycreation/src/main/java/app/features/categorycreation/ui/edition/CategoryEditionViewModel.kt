package app.features.categorycreation.ui.edition

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
     *
     * @param id
     */
    fun loadCategory(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(1000)
            val category = repository.getCategoryById(id)
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
     *
     * @param event
     */
    fun onEvent(event: CategoryEditionEvent) {
        when (event) {
            is CategoryEditionEvent.OnNameChange -> state =
                state.copy(name = event.name, isNameError = event.name.isEmpty())

            is CategoryEditionEvent.OnShortNameChange -> state = state.copy(
                shortName = event.shortName,
                isShortNameError = event.shortName.isEmpty()
            )

            is CategoryEditionEvent.OnDescriptionChange -> state = state.copy(
                description = event.description,
                isDescriptionError = event.description.isEmpty()
            )

            is CategoryEditionEvent.OnTypeCategoryChange -> state =
                state.copy(typeCategory = event.typeCategory)

            is CategoryEditionEvent.ConfirmChanges -> confirmChanges()
        }
    }

    /**
     * Confirm changes
     *
     */
    private fun confirmChanges() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            if (validateFields()) {
                state.category?.copy(
                    name = state.name,
                    shortName = state.shortName,
                    description = state.description,
                    typeCategory = state.typeCategory
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
     *
     * @return
     */
    private fun validateFields(): Boolean {
        val hasError =
            state.name.isEmpty() || state.shortName.isEmpty() || state.description.isEmpty()
        state = state.copy(
            isNameError = state.name.isEmpty(),
            isShortNameError = state.shortName.isEmpty(),
            isDescriptionError = state.description.isEmpty(),
            isError = hasError
        )
        return !hasError
    }

    fun onFungibleChange(fungible: Boolean) {
        state = state.copy(fungible = fungible)
    }

    fun onImageChange(image: Uri) {
        state = state.copy(image = image)
    }
}
