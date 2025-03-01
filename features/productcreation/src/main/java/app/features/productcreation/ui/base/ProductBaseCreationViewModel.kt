package app.features.productcreation.ui.base

import android.content.res.Resources
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.base.utils.format
import app.domain.invoicing.category.Category
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.DependencyRepository
import app.domain.invoicing.repository.SectionRepository
import app.domain.invoicing.section.Section
import app.features.productcreation.R
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

/**
 * Una clase abtracta que recoge las funciones y variables comunes que se necesitan
 * en las pantallas de creación y edición de productos.
 *
 * @param resources Necesario para acceder a los recursos de la aplicación fuera de los composabes.
 *
 */
abstract class ProductBaseCreationViewModel(
    protected val resources: Resources
) : ViewModel() {
    var productViewState by mutableStateOf(ProductViewState())
        protected set

    protected lateinit var onGoBackNav : () -> Unit

    protected var productIsBeingAdded = false

    protected var allExistingSections = emptyList<Section>()

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
     * Cuando el campo de la seleccion de imagen ha sido modificado
     *
     * @param newUri La nueva [Uri] de la imagen.
     */
    fun onNewImageSelected(newUri : Uri?){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                uriImage = newUri
            )
        )
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
        val minimunLength = 3
        fun notRightLength() = newShort.length < minimunLength
        fun thereAreSpecialCharacter() = Regex("[^a-zA-Z0-9]").containsMatchIn(newShort)
        when {
            notRightLength() -> shortNameChanged(
                newShort,
                true,
                resources.getString(R.string.error_short_name_not_rigth_length, minimunLength)
            )
            thereAreSpecialCharacter() -> shortNameChanged(
                newShort,
                true,
                resources.getString(R.string.error_short_name_special_caracters)
            )
            else -> shortNameChanged(newShort, false)
        }
    }

    private fun shortNameChanged(newShortName: String, error : Boolean, errorText: String = "") {
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                shortName = newShortName
            ),
            errorDataState = productViewState.errorDataState.copy(
                shortNameError = error,
                shortNameText = errorText
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
    fun onStockChanged(newStock : String){
        val minimunValue = 0u
        fun notAUintNumber() = newStock.toUIntOrNull() == null
        fun notEnougthStock() = newStock.toUInt() <= minimunValue
        when {
            notAUintNumber() -> stockChanged(
                newStock,
                true,
                resources.getString(R.string.error_value_must_be_numeric)
            )
            notEnougthStock() -> stockChanged(
                newStock,
                true,
                resources.getString(R.string.error_value_not_correct_range, minimunValue.toString())
            )
            else -> stockChanged(newStock, false)
        }
    }

    private fun stockChanged(newStock : String, error : Boolean, errorText : String = ""){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                stock = newStock
            ),
            errorDataState = productViewState.errorDataState.copy(
                stockError = error,
                stockText = errorText
            )
        )
    }

    /**
     * Cuando el campo del Stock minimo se modifica.
     *
     * @param newMinimunStock El nuevo stock mínimo cuando se modifica
     */
    fun onMinimunStockChanged(newMinimunStock : String){
        if (newMinimunStock.isEmpty()){
            minimunStockChanged("", false)
            return;
        }
        val minimunValue = 0u
        fun notAUintNumber() = newMinimunStock.toUIntOrNull() == null
        fun notEnougthStock() = newMinimunStock.toUInt() <= minimunValue
        when {
            notAUintNumber() -> minimunStockChanged(
                newMinimunStock,
                true,
                resources.getString(R.string.error_value_must_be_numeric)
            )
            notEnougthStock() -> minimunStockChanged(
                newMinimunStock,
                true,
                resources.getString(R.string.error_value_not_correct_range, minimunValue.toString())
            )
            else -> minimunStockChanged(newMinimunStock, false)
        }
    }

    private fun minimunStockChanged(newStock : String, error : Boolean, errorText : String = ""){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                minimunStock = newStock
            ),
            errorDataState = productViewState.errorDataState.copy(
                minimunStockError = error,
                minimunStockText = errorText
            )
        )
    }

    /**
     * Cuando el campo del precio se modifica.
     *
     * @param newPrice El núevo precio introducido por el usuario.
     */
    fun onPriceChanged(newPrice : String){
        val minimunPrice = 0
        val price = newPrice.toDoubleOrNull()
        fun notANumber() = price == null
        fun isNotPositive() = price!! < minimunPrice
        when {
            notANumber() ->  priceChanged(
                newPrice,
                true,
                resources.getString(R.string.error_value_must_be_numeric)
            )
            isNotPositive() -> priceChanged(
                newPrice,
                true,
                resources.getString(R.string.error_value_not_correct_range, minimunPrice.toString())
            )
            else -> priceChanged(newPrice, false)
        }
    }

    private fun priceChanged(newPrice : String, error: Boolean, errorText : String = ""){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                price = newPrice
            ),
            errorDataState = productViewState.errorDataState.copy(
                priceError = error,
                priceText = errorText
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
    fun onNewSectionSelected(newSection : Section){
        productViewState = productViewState.copy(
            inputDataState = productViewState.inputDataState.copy(
                selectedSection = newSection
            )
        )
    }

    /**
     * Cuando el campo de las dependencias se ha modificado.
     *
     * Cabe destacar que este metodo también filtra las secciones seleccionables
     * en [ProductViewState.sectionsList] para que las secciones que aparezcan
     * sean pertenecientes a la dependencia seleccionada en [ProductViewState.inputDataState]
     *
     * @param newDependency La nueva dependencia seleccionada por el usuario.
     */
    fun onNewDependencySelected(newDependency: Dependency){
        productViewState = productViewState.copy(
            sectionsList = getSelectableSectionsFrom(newDependency),
            inputDataState = productViewState.inputDataState.copy(
                selectedDependency = newDependency,
                selectedSection = null
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
            errorDataState = productViewState.errorDataState.copy(
                cantRegisterProduct = false
            ),
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
                    errorDataState = productViewState.errorDataState.copy(
                        cantRegisterProduct = true
                    ),
                )
            }
            else -> whenNoErrorFound()
        }
    }

    private fun isThereAnActiveError(errorDataState: ErrorDataState) =
        errorDataState.priceError || errorDataState.stockError || errorDataState.shortNameError
                || errorDataState.minimunStockError

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
                inputDataState.selectedDependency == null ||
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
            image = productData.uriImage,
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

    /**
     * Advertencias: Este metodo debe ser usando cuando [ProductBaseCreationViewModel.allExistingSections]
     * haya sido inicializado.
     *
     * Permite obtener una lista de [Section] que pertenezcan a la [Dependency]
     * pasada por parametro.
     *
     * @return Una lista con las [Section] que pertenezcan a la [Dependency] pasada por parametro
     * cuando esté no es nulo, y si lo es, devuelve una lista vacia.
     *
     */
    protected fun getSelectableSectionsFrom(selectedDependency : Dependency?) : List<Section>{
        return if (selectedDependency != null)
            allExistingSections.filter {
                it.belongedDependency == selectedDependency
            }
         else
             emptyList()
    }

    /**
     * Permite mostrar un mensaje en la vista a traves de un SnackBar.
     *
     * @param message El mensaje a mostrar a traves del SnackBar.
     */
    protected fun showSnackBar(message : String){
        viewModelScope.launch {
            productViewState.snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = true
            )
        }
    }

    /**
     * Permite obtener todas las categorias de su repositorio de forma asincrona.
     *
     * @return Un objeto [Deferred] con la lista de [Category] existentes.
     */
    protected suspend fun getCategoriesAsync() : Deferred<List<Category>> {
        return viewModelScope.async(Dispatchers.IO) {
            delay(1000)
            CategoryRepository.getAllCategories()
        }
    }

    /**
     * Permite obtener todas las secciones de su repositorio de forma asincrona.
     *
     * @return Un objeto [Deferred] con la lista de [Section] existentes.
     */
    protected suspend fun getSectionsAsync() : Deferred<List<Section>>{
        return viewModelScope.async (Dispatchers.IO) {
            (SectionRepository.getAllSections() as BaseResult.Success).data.first()
        }
    }

    /**
     * Permite obtener todas las dependencias de su repositorio de forma asincrona
     *
     * @return Un objeto [Deferred] con la lista de [Dependency] existentes.
     */
    protected suspend fun getDependenciesAsync() : Deferred<List<Dependency>>{
        return viewModelScope.async {
            (DependencyRepository.getAllDependencies() as BaseResult.Success).data.first()
        }
    }
}