package app.features.productcreation.ui.creation

import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import kotlinx.coroutines.launch

class ProductCreationViewModel(onGoBackNav : () -> Unit = {}) : ProductBaseCreationViewModel(onGoBackNav) {
    override fun onAcceptChanges() {
        val errorDataState = productViewState.errorDataState
        if (areThereAnyEmptyOrNotSelectedOnObligatoryFields()){
            productViewState = productViewState.copy(
                errorDataState = errorDataState.copy(
                    emptyFields = true
                )
            )
            return
        }
        if (errorDataState.priceError || errorDataState.stockError || errorDataState.shortNameError)
            productViewState = productViewState.copy(
                errorDataState = errorDataState.copy(
                    cantRegisterProduct = true
                )
            )
        else{
            viewModelScope.launch {
                ProductRepository.addProduct(makeProductFromFields())
            }
            onGoBackNav()
        }
    }

    private fun areThereAnyEmptyOrNotSelectedOnObligatoryFields() : Boolean{
        val inputDataState = productViewState.inputDataState
        return inputDataState.name.isEmpty() ||
                inputDataState.shortName.isEmpty() ||
                inputDataState.code.isEmpty() ||
                inputDataState.serieNumber.isEmpty() ||
                inputDataState.modelCode.isEmpty() ||
                inputDataState.productType.isEmpty() ||
                inputDataState.price.isEmpty() ||
                inputDataState.stock.isEmpty() ||
                inputDataState.adquisitionDate == null ||
                inputDataState.selectedCategory == null ||
                inputDataState.selectedSection == null ||
                inputDataState.description.isEmpty()
    }

    private fun makeProductFromFields() : Product{
        val productData = productViewState.inputDataState
        return Product(
            code = productData.code,
            name = productData.name,
            shortName = productData.shortName,
            description = productData.description,
            serialNumber = productData.serieNumber,
            modelCode = productData.modelCode,
            productType = productData.productType,
            category = productData.selectedCategory!! ,
            section = productData.selectedSection!! ,
            stock = productData.stock.toUInt(),
            price = productData.price.toDouble(),
            acquisitionDate = productData.adquisitionDate!!,
            discontinuationDate = productData.discontinuationDate,
            notes = productData.notes,
            tags = Tags(productData.tags.split(",").map {
                it.trim()
                Tag(it)
            }) ,
            minimunStock = productData.minimunStock.toUIntOrNull()
        )
    }
}