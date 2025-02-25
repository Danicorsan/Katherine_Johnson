package app.features.productcreation.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.base.utils.format
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import kotlinx.datetime.Instant

/**
 * Una clase abtracta que recoge las funciones y variables comunes que se necesitan
 * en las pantallas de creación y edición de productos
 *
 */
abstract class ProductBaseCreationViewModel : ViewModel() {
    var productViewState by mutableStateOf(ProductViewState())
        protected set

    protected lateinit var onGoBackNav : () -> Unit

    protected var productIsBeingAdded = false

    /**
     * Se ejecuta cuando el usuario decide querer guardar los cambios.
     * Aquí tambien se realizan las comprobaciónes para determinar
     * si la información es correcta.
     *
     */
    abstract fun onAcceptChanges()

    /**
     * Necesario para establecer el evento para volver a la pantalla
     * anterior.
     *
     * @param onGoBackNav El evento para volver a la pantalla anterior.
     * @receiver
     */
    fun stablishNavigationEvent(onGoBackNav : () -> Unit){
        this.onGoBackNav = onGoBackNav
    }

    /**
     * Cuando el campo para el código del producto se modifica.
     *
     * @param newCode El codigo introducido por el usuario.
     */
    fun onCodeChanged(newCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                code = newCode
            )
        )
    }

    /**
     * Cuando el campo para el nombre del producto se modifica.
     *
     * @param newName El nuevo nombre introducido por el usuario.
     */
    fun onNameChanged(newName : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                name = newName
            )
        )
    }

    /**
     * Cuando el campo para el nombre corto del producto se modifica.
     *
     * @param newShort El núevo nombre corto introducido por el usuario.
     */
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

    /**
     * Cuando el campo de la descripción de un producto se modifica.
     *
     * @param newDescription La nueva descripción introducida por el usuario.
     */
    fun onDescriptionChanged(newDescription : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                description = newDescription
            )
        )
    }

    /**
     * Cuando campo del número de serie del producto se modifica.
     *
     * @param newSerialNumber El nuevo número de serie del producto.
     */
    fun onSerialNumberChanged(newSerialNumber : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                serieNumber = newSerialNumber
            )
        )
    }

    /**
     * Cuando el campo el código de modelo del producto se modifica.
     *
     * @param newModelCode El núevo código de modelo del producto introducido por el usuario.
     */
    fun onModelCodeChanged(newModelCode : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                modelCode = newModelCode
            )
        )
    }

    /**
     * Cuando el campo del tipo de producto se modifica.
     *
     * @param newProductType El nuevo tipo de producto introducido por el usuario.
     */
    fun onProductTypeChanged(newProductType : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                productType = newProductType
            )
        )
    }

    /**
     * Cuando el campo del stock de un producto se modifica.
     *
     * @param newStock El núevo stock introducido por el usuario.
     */
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

    /**
     * Cuando el campo del precio se modifica.
     *
     * @param newPrice El núevo precio introducido por el usuario.
     */
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

    /**
     * Cuando el campo del Stock minimo se modifica.
     *
     * La implementación de Alarma de un producto cuando llega a cierto stock mínimo
     * no se ha implementado. Por lo que el evento todavia no está en uso.
     *
     * @param newMinimunStock El nuevo stock mínimo cuando se modifica
     */
    fun onMinimunStockChanged(newMinimunStock : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                minimunStock = newMinimunStock
            )
        )
    }

    /**
     * Cuando el campo de la categoría es modificado.
     *
     * @param newCategory La nueva categoría seleccionada por el usuario.
     */
    fun onNewCategorySelected(newCategory : Category){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                selectedCategory = newCategory
            )
        )
    }

    /**
     * Cuando el campo de las secciones se ha modificado.
     *
     * @param newSection La nueva sección introducida por el usuario.
     */
    fun onNewSectionSelected(newSection : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                selectedSection = newSection
            )
        )
    }

    /**
     * Cuando el campo de la fecha de adquisición es modificado.
     *
     * @param newAcquisitionDate La nueva fecha de adquisición introducida por el usuario.
     */
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

    /**
     * Cuando el campo de la fecha de discontinuación a sido modificado.
     *
     * @param newDiscontinuationDate La nueva fecha de discontinuación introducida por el usuario.
     */
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

    /**
     * Cuando el campo de notas haya sido modificado.
     *
     * @param newNotes Las nuevas notas introducidas por el usuario.
     */
    fun onNotesChanged(newNotes : String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                notes = newNotes
            )
        )
    }

    /**
     * Cuando el campo de etiquetas del producto es modificado.
     *
     * @param newTags Las nuevas etiquetas del producto.
     */
    fun onTagsChanged(newTags: String){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                tags = newTags
            )
        )
    }

    /**
     * Cuando el usuario haya leido y aceptado el cuadro informativo cuando
     * no se puede registrar o modificar un producto.
     *
     */
    fun onDismissCantRegisterAlertDialog(){
        productViewState = productViewState.copy(
            cantRegisterProduct = false
        )
    }

    /**
     * Cuando el usuario haya leido y aceptado el cuadro informativo cuando
     * hay campos obligatorios que están vacios.
     *
     */
    fun onDismissEmptyFieldsAlertDialog(){
        productViewState = productViewState.copy(
            errorDataState = productViewState.errorDataState.copy(
                emptyFields = false
            )
        )
    }

    /**
     *  Cuando el usuario haya leido y aceptado el cuadro informativo cuando
     *  el producto haya sido guardado con exito.
     *
     */
    fun onDismissProductHasBeenRegisteredAlertDialog(){
        onGoBackNav()
    }

    /**
     * Cuando el usuario decide irse de la pantalla sin guardar los
     * cambios.
     *
     */
    fun onLeavePage(){
        onGoBackNav()
    }

    /**
     * Permite la comprobación de introduciones no permitidas en los campos
     * y modifica el [ProductBaseCreationViewModel.productViewState]
     * cuando encuentra un error.
     *
     * @param whenNoErrorFound Acción a ejecutar cuando se haya determinado que no hay información
     * erronea o no permitida en los campos.
     * @receiver
     */
    protected fun comprobateAndManageLocalErrors(whenNoErrorFound : () -> Unit){
        if (productViewState.isLoading || productIsBeingAdded)
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
                    cantRegisterProduct = true
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

    /**
     * Permite crear un productos a partir de la información almacenada en los campos.
     *
     * La comprobación de errores debe realizarse anteriormente.
     *
     * @return Un producto que recoge toda la información de los campos en
     * [ProductBaseCreationViewModel.productViewState]
     */
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