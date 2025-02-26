package app.features.productcreation.ui.creation

import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * El ViewModel correspondiente a [app.features.productcreation.ui.creation.ProductCreationScreen]
 *
 */
@HiltViewModel
class ProductCreationViewModel @Inject constructor() : ProductBaseCreationViewModel() {

    /**
     * Carga e inicializa los datos necesarios para [app.features.productcreation.ui.creation.ProductCreationScreen]
     *
     */
    fun loadScreenData() {
        productViewState = productViewState.copy(isLoading = true)
        viewModelScope.launch {
            val deferredCategories = getCategoriesAsync()
            val deferredSections = getSectionsAsync()
            val deferredDependencies = getDependenciesAsync()
            allExistingSections = deferredSections.await()
            productViewState = productViewState.copy(
                isLoading = false,
                categoriesList = deferredCategories.await(),
                dependenciesList = deferredDependencies.await()
            )
        }
    }

    override fun onAcceptChanges() {
        comprobateAndManageLocalErrors{
            this.productIsBeingAdded = true
            productViewState = productViewState.copy(
                isLoading = true
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