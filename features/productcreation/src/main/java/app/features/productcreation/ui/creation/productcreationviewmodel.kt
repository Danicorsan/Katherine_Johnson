package app.features.productcreation.ui.creation

import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductCreationViewModel(onGoBackNav : () -> Unit = {}) : ProductBaseCreationViewModel(onGoBackNav) {

    init {
        productViewState = productViewState.copy(isLoading = true)
        viewModelScope.launch {
            delay(2000)//Simulacion de tiempo de espera
            val categories = CategoryRepository.getAllCategories()
            productViewState = productViewState.copy(
                isLoading = false,
                categoriesList = categories,
                sectionsList = listOf("Sección 1", "Sección 2", "Sección 3",
                    "Sección 1", "Sección 2", "Sección 3",
                    "Sección 1", "Sección 2", "Sección 3",
                    "Sección 1", "Sección 2", "Sección 3")
            )
        }
    }

    override fun onAcceptChanges() {
        comprobateAndManageLocalErrors{
            productViewState = productViewState.copy(
                isLoading = true,
                productIsBeingAdded = true
            )
            viewModelScope.launch {
                ProductRepository.addProduct(makeProductFromFields())
                productViewState = productViewState.copy(
                    isLoading = false,
                    productRegisterSuccessful = true
                )
            }
        }
    }
}