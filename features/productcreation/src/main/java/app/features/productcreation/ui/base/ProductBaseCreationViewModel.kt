package app.features.productcreation.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.format
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

abstract class ProductBaseCreationViewModel(protected val onGoBackNav : () -> Unit) : ViewModel() {
    var productViewState by mutableStateOf(ProductViewState())
        protected set

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

    fun onCodeChanged(newCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                code = newCode
            )
        )
    }

    fun onNameChanged(newName : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                name = newName
            )
        )
    }

    fun onShortNameChanged(newShort : String) {
        fun hasRightLength() = newShort.length <= 3
        fun thereAreSpecialCharacter() = Regex("[^a-zA-Z0-9]").containsMatchIn(newShort)
        if (hasRightLength() || thereAreSpecialCharacter()) {
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

    fun onDescriptionChanged(newDescription : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                description = newDescription
            )
        )
    }

    fun onSerialNumberChanged(newSerialNumber : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                serieNumber = newSerialNumber
            )
        )
    }

    fun onModelCodeChanged(newModelCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                modelCode = newModelCode
            )
        )
    }

    fun onProductTypeChanged(newProductType : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                productType = newProductType
            )
        )
    }

    fun onStockChange(newStock : String){
        fun notAUintNumber() = newStock.toUIntOrNull() == null
        fun notEnougthStock() = newStock.toUInt() < 1u
        if (notAUintNumber() || notEnougthStock()){
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

    fun onPriceChanged(newPrice : String){
        val price = newPrice.toDoubleOrNull()
        fun itIsANumber() = price == null
        fun isNotPositive() = price!! < 0
        if (itIsANumber() || isNotPositive()){
            priceChanged(newPrice, true)
        } else {
            priceChanged(newPrice, false)
        }
    }

    private fun priceChanged(newPrice : String, error: Boolean){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                price = newPrice
            ),
            errorDataState = productViewState.errorDataState.copy(
                priceError = error
            )
        )
    }

    fun onMinimunStockChanged(newMinimunStock : String){
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

    fun onAcquisitonDateChanged(newAcquisitionDate : Long?){
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

    fun onDiscontinuationDateChanged(newDiscontinuationDate : Long?){
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

    fun onNotesChanged(newNotes : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                notes = newNotes
            )
        )
    }

    fun onTagsChanged(newTags: String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                tags = newTags
            )
        )
    }

    fun onDismissCantRegisterAlertDialog(){
        productViewState = productViewState.copy(
            errorDataState = productViewState.errorDataState.copy(
                cantRegisterProduct = false
            )
        )
    }

    fun onDismissEmptyFieldsAlertDialog(){
        productViewState = productViewState.copy(
            errorDataState = productViewState.errorDataState.copy(
                emptyFields = false
            )
        )
    }

    fun onDismissProductHasBeenRegisteredAlertDialog(){
        productViewState = productViewState.copy(
            productRegisterSuccessful = false
        )
        onGoBackNav()
    }

    fun onLeavePage(){
        onGoBackNav()
    }

    abstract fun onAcceptChanges()

    protected fun comprobateAndManageLocalErrors(whenNoErrorFound : () -> Unit){
        if (productViewState.isLoading || productViewState.productIsBeingAdded)
            return

        val errorDataState = productViewState.errorDataState
        when{
            areThereAnyEmptyOrNotSelectedOnObligatoryFields() -> {
                productViewState = productViewState.copy(
                    errorDataState = errorDataState.copy(
                        emptyFields = true
                    )
                )
            }
            isThereAnActiveError(errorDataState) -> {
                productViewState = productViewState.copy(
                    errorDataState = errorDataState.copy(
                        cantRegisterProduct = true
                    )
                )
            }
            else -> whenNoErrorFound()
        }
    }

    private fun isThereAnActiveError(errorDataState: ErrorDataState) =
        errorDataState.priceError || errorDataState.stockError || errorDataState.shortNameError

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

    protected fun makeProductFromFields() : Product {
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