package app.features.productcreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import app.base.utils.format
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import kotlinx.coroutines.launch


class ProductEditionViewModel(
    onGoBackNav: () -> Unit = {},
    productToEditId: Int
) : ProductBaseCreationViewModel(onGoBackNav) {

    init {
        viewModelScope.launch {
            //TODO(Añadir en el futuro código de control en caso de devolver BaseResult.Error)
            //Se podria hacer una composicion y tener separado un productView state para creacion y
            //otro para edición
            val response = ProductRepository.getProductById(productToEditId) as BaseResult.Success
            val productToEdit = response.data
            productViewState = productViewState.copy(
                inputDataState = productViewState.inputDataState.copy(
                    code = productToEdit.code,
                    name = productToEdit.name,
                    shortName = productToEdit.shortName,
                    description = productToEdit.description,
                    serieNumber = productToEdit.serialNumber,
                    modelCode = productToEdit.modelCode,
                    productType = productToEdit.productType,
                    stock = productToEdit.stock.toString(),
                    price = productToEdit.price.toString(),
                    minimunStock = productToEdit.minimunStock?.toString() ?: "",
                    adquisitionDate = productToEdit.acquisitionDate,
                    adquisitionDateRepresentation = productToEdit.acquisitionDate.format(),
                    discontinuationDate = productToEdit.discontinuationDate,
                    discontinuationDateRepresentation = productToEdit.discontinuationDate?.format(),
                    selectedCategory = productToEdit.category,
                    selectedSection = productToEdit.section,
                    notes = productToEdit.notes,
                    tags = productToEdit.tags.toString()
                )
            )
        }
    }

    override fun onAcceptChanges() {
        //TODO(Establecer el estado del producto a "modified")
        onGoBackNav()
    }
}