package app.features.productcreation.ui.creation

import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import kotlinx.coroutines.launch

class ProductCreationViewModel(onGoBackNav : () -> Unit = {}) : ProductBaseCreationViewModel(onGoBackNav) {
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