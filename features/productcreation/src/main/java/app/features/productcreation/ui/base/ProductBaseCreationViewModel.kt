package app.features.productcreation.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.format
import app.domain.invoicing.category.Category
import app.domain.invoicing.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import java.util.concurrent.atomic.AtomicBoolean

abstract class ProductBaseCreationViewModel : ViewModel() {
    var productViewState by mutableStateOf(ProductViewState())
        private set

    var dropDownItemsState by mutableStateOf(DropDownItemsState())
        private set

    private val isReady = AtomicBoolean()

    fun onCodeChange(newCode : String){
        productViewState = productViewState.copy(code = newCode)
    }

    fun onNameChange(newName : String){
        productViewState = productViewState.copy(name = newName)
    }

    fun onShortNameChange(newShort : String){
        productViewState = productViewState.copy(shortName = newShort)
    }

    fun onDescriptionChange(newDescription : String){
        productViewState = productViewState.copy(description = newDescription)
    }

    fun onSerialNumberChange(newSerialNumber : String){
        productViewState = productViewState.copy(serialNumber = newSerialNumber)
    }

    fun onModelCodeChange(newModelCode : String){
        productViewState = productViewState.copy(modelCode = newModelCode)
    }

    fun onProductTypeChange(newProductType : String){
        productViewState = productViewState.copy(productType = newProductType)
    }

    fun onStockChange(newStock : String){
        productViewState = productViewState.copy(stock = newStock)
    }

    fun onPriceChange(newPrice : String){
        productViewState = productViewState.copy(price = newPrice)
    }

    fun onMinimunStockChange(newMinimunStock : String){
        productViewState = productViewState.copy(minimunStock = newMinimunStock)
    }

    fun onNewCategorySelected(newCategory : Category){
        dropDownItemsState = dropDownItemsState.copy(selectedCategory = newCategory)
    }

    fun onNewSectionSelected(newSection : String){
        dropDownItemsState = dropDownItemsState.copy(selectedSection = newSection)
    }

    fun onAcquisitonDateChange(newAcquisitionDate : Long?){
        newAcquisitionDate?.let {
            val adquisitionDate = Instant.fromEpochMilliseconds(newAcquisitionDate)
            productViewState = productViewState.copy(
                adquisitionDate = adquisitionDate,
                adquisitionDateRepresentation = adquisitionDate.format()
            )
        } ?: run {
            productViewState = productViewState.copy(
                adquisitionDate = null,
                adquisitionDateRepresentation = null
            )
        }
    }

    fun onDiscontinuationDateChange(newDiscontinuationDate : Long?){
        newDiscontinuationDate?.let {
            val discontinuationDate = Instant.fromEpochMilliseconds(newDiscontinuationDate)
            productViewState = productViewState.copy(
                discontinuationDate = discontinuationDate,
                discontinuationDateRepresentation = discontinuationDate.format()
            )
        } ?: run {
            productViewState = productViewState.copy(
                discontinuationDate = null,
                discontinuationDateRepresentation = null
            )
        }
    }

    fun getReady(){
        if (!isReady.get()) {
            productViewState = productViewState.copy(isLoading = true)
            viewModelScope.launch {
                delay(2000)//Simulacion de tiempo de espera
                val categories = CategoryRepository.getAllCategories()
                isReady.set(true)
                dropDownItemsState = dropDownItemsState.copy(categoriesList = categories)
                productViewState = productViewState.copy(isLoading = false)
            }
        }
    }

    open fun onLeavePage(){
        isReady.set(false)
    }

    open fun onAcceptChanges(){
        isReady.set(false)
    }
}