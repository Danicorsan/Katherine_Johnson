package app.features.productcreation.ui.creation

import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCreationViewModel @Inject constructor() : ProductBaseCreationViewModel() {

    fun loadScreenData() {
        productViewState = productViewState.copy(isLoading = true)
        viewModelScope.launch {
            delay(1000)//Simulacion de tiempo de espera
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