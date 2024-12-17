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

    private val isReady = AtomicBoolean()

    protected lateinit var onGoBackNav : () -> Unit
        private set

    fun onCodeChange(newCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                code = newCode
            )
        )
    }

    fun onNameChange(newName : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                name = newName
            )
        )
    }

    fun onShortNameChange(newShort : String) {
        val noSpecialCharacter = Regex("[^a-zA-Z0-9]")
        if (newShort.length <= 3 || noSpecialCharacter.containsMatchIn(newShort)) {
            shortNameChanged(newShort, true)
        } else {
            shortNameChanged(newShort, false)
        }
    }

    private fun shortNameChanged(newShortName: String, error : Boolean) {
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                shortName = newShortName
            ),
            errorDataState = productViewState.errorDataState.copy(
                shortNameError = error
            )
        )
    }

    fun onDescriptionChange(newDescription : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                description = newDescription
            )
        )
    }

    fun onSerialNumberChange(newSerialNumber : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                serialNumber = newSerialNumber
            )
        )
    }

    fun onModelCodeChange(newModelCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                modelCode = newModelCode
            )
        )
    }

    fun onProductTypeChange(newProductType : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                productType = newProductType
            )
        )
    }

    fun onStockChange(newStock : String){
        if (newStock.contains(".") || newStock.toInt() <= 1){
            stockChanged(newStock, true)
        } else{
            stockChanged(newStock, false)
        }
    }

    private fun stockChanged(newStock : String, error : Boolean){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                stock = newStock
            ),
            errorDataState = productViewState.errorDataState.copy(
                stockError = error
            )
        )
    }

    fun onPriceChange(newPrice : String){
        val price = newPrice.toDoubleOrNull()
        if (price == null || price <= 0){
            priceChanged(newPrice, true)
        } else {
            priceChanged(newPrice, false)
        }
    }

    fun priceChanged(newPrice : String, error: Boolean){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                price = newPrice
            ),
            errorDataState = productViewState.errorDataState.copy(
                priceError = error
            )
        )
    }

    fun onMinimunStockChange(newMinimunStock : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                minimunStock = newMinimunStock
            )
        )
    }

    fun onNewCategorySelected(newCategory : Category){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                selectedCategory = newCategory
            )
        )
    }

    fun onNewSectionSelected(newSection : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                selectedSection = newSection
            )
        )
    }

    fun onAcquisitonDateChange(newAcquisitionDate : Long?){
        newAcquisitionDate?.let {
            val adquisitionDate = Instant.fromEpochMilliseconds(newAcquisitionDate)
            productViewState = productViewState.copy(
                inputDataState = productViewState.inputDataState.copy(
                    adquisitionDate = adquisitionDate,
                    adquisitionDateRepresentation = adquisitionDate.format()
                ),
            )
        } ?: run {
            productViewState = productViewState.copy(
                inputDataState = productViewState.inputDataState.copy(
                    adquisitionDate = null,
                    adquisitionDateRepresentation = null
                ),
            )
        }
    }

    fun onDiscontinuationDateChange(newDiscontinuationDate : Long?){
        newDiscontinuationDate?.let {
            val discontinuationDate = Instant.fromEpochMilliseconds(newDiscontinuationDate)
            productViewState = productViewState.copy(
                inputDataState = productViewState.inputDataState.copy(
                    discontinuationDate = discontinuationDate,
                    discontinuationDateRepresentation = discontinuationDate.format()
                )
            )
        } ?: run {
            productViewState = productViewState.copy(
                inputDataState = productViewState.inputDataState.copy(
                    discontinuationDate = null,
                    discontinuationDateRepresentation = null
                )
            )
        }
    }

    fun getReady(onGoBackNav : () -> Unit){
        if (!isReady.get()) {
            productViewState = productViewState.copy(isLoading = true)
            this.onGoBackNav = onGoBackNav
            viewModelScope.launch {
                delay(2000)//Simulacion de tiempo de espera
                val categories = CategoryRepository.getAllCategories()
                isReady.set(true)
                productViewState = productViewState.copy(
                    isLoading = false,
                    categoriesList = categories
                )
            }
        }
    }

    fun onLeavePage(){
        isReady.set(false)
        onGoBackNav()
    }

    open fun onAcceptChanges(){
        isReady.set(false)
    }
}