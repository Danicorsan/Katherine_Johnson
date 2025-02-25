package app.features.productcreation.ui.edition

import androidx.lifecycle.viewModelScope
import app.base.utils.format
import app.domain.invoicing.category.Category
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.ProductRepository
import app.features.productcreation.ui.base.ProductBaseCreationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * El ViewModel correspondiente a la pantalla de edición de productos.
 *
 */
@HiltViewModel
class ProductEditionViewModel @Inject constructor() : ProductBaseCreationViewModel() {

    private var productToEditId : Int = -1

    /**
     * Carga los datos necesarios para crear un producto, como cuales son
     * las categorias y secciones existentes.
     *
     * Ademas, en edición se debe cargar del respositorios los datos
     * del productos a traves de su ID
     *
     * @param productToEditId El ID del producto que queremos editar.
     *
     */
    fun loadScreenData(productToEditId: Int) {
        this.productToEditId = productToEditId
        productViewState = productViewState.copy(isLoading = true)
        viewModelScope.launch {
            val categoriesDeferred = async(Dispatchers.IO) { getCategories() }
            val productDeferred = async(Dispatchers.IO) { getProductById(productToEditId) }
            val sections = getSection()
            val categories = categoriesDeferred.await()
            val product = productDeferred.await()

            productViewState = productViewState.copy(
                isLoading = false,
                categoriesList = categories,
                sectionsList = sections,
                inputDataState = productViewState.inputDataState.copy(
                    code = product.code,
                    name = product.name,
                    shortName = product.shortName,
                    description = product.description,
                    serieNumber = product.serialNumber,
                    modelCode = product.modelCode,
                    productType = product.productType,
                    stock = product.stock.toString(),
                    price = product.price.toString(),
                    minimunStock = product.minimunStock?.toString() ?: "",
                    adquisitionDate = product.acquisitionDate,
                    adquisitionDateRepresentation = product.acquisitionDate.format(),
                    discontinuationDate = product.discontinuationDate,
                    discontinuationDateRepresentation = product.discontinuationDate?.format(),
                    selectedCategory = product.category,
                    selectedSection = product.section,
                    notes = product.notes,
                    tags = product.tags.toString()
                )
            )
        }
    }

    private suspend fun getProductById (productToEditId: Int) : Product =
        (ProductRepository.getProductById(productToEditId) as BaseResult.Success).data

    private suspend fun getCategories() : List<Category> {
        delay(1000)
        return CategoryRepository.getAllCategories()
    }

    private fun getSection() = listOf( //Se espera que esté metodo se vuelva realmete asincrono en el futuro
            "Sección 1", "Sección 2", "Sección 3",
            "Sección 1", "Sección 2", "Sección 3",
            "Sección 1", "Sección 2", "Sección 3",
            "Sección 1", "Sección 2", "Sección 3"
    )

    override fun onAcceptChanges() {
        comprobateAndManageLocalErrors {
            productIsBeingAdded = true
            productViewState = productViewState.copy(
                isLoading = true
            )
            viewModelScope.launch {
                val productToUpdate = makeProductFromFields().copy(
                    id = productToEditId,
                    state = ProductState.modified
                )
                ProductRepository.updateExistingProduct(productToUpdate)
                productViewState = productViewState.copy(
                    isLoading = false,
                    productRegisterSuccessful = true
                )
            }
        }
    }
}