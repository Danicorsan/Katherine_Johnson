package app.features.productcreation.ui.base

import android.net.Uri
import app.domain.invoicing.category.Category
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.section.Section
import kotlinx.datetime.Instant

/**
 * Guarda el estado de la vista en este Data class tanto para creación como
 * edición de productos.
 *
 * @property inputDataState Objeto [InputDataState] para almacenar toda la información a mostrar en los campos.
 * @property errorDataState Objeto [ErrorDataState] para guardar las variables relacionadas con la gestión de errores.
 * @property isLoading Indica si la pantalla está cargando.
 * @property categoriesList La lista de [Category] seleccionables.
 * @property sectionsList La lista de [Section] seleccionables.
 * @property dependenciesList La lista de [Dependency] seleccionables.
 * @property productRegisterSuccessful Indica si se han guardado los cambios con exito.
 * @property cantRegisterProduct Indica si ha habido un error al querer guardar los cambios.
 */
data class ProductViewState(
    val inputDataState: InputDataState = InputDataState(),
    val errorDataState: ErrorDataState = ErrorDataState(),
    val isLoading : Boolean = false,
    val categoriesList : Iterable<Category> = emptyList(),
    val sectionsList : Iterable<Section> = emptyList(),
    val dependenciesList : Iterable<Dependency> = emptyList(),
    val productRegisterSuccessful : Boolean = false,
    val cantRegisterProduct : Boolean = false,
)

/**
 * Data class que recoge toda la información de los campos del formulario de productos.
 *
 * @property code El código del producto.
 * @property name El nombre del producto
 * @property shortName El nombre corto del producto.
 * @property description La descripción del producto.
 * @property serieNumber El número de serie del producto.
 * @property modelCode El código de modelo del producto.
 * @property productType El tipo de producto.
 * @property stock El stock del producto.
 * @property uriImage El [Uri] de la imagen seleccionada, o null si no hay ninguna seleccionada.
 * @property price El precio del producto.
 * @property minimunStock El stock mínimo del producto. (Todavia sin usar)
 * @property adquisitionDate La fecha de adquisición del producto a traves de un objeto [Instant]
 * @property adquisitionDateRepresentation La representación de la fecha de adquisición como un String
 * @property discontinuationDate La fecha de discontinuación a traves de un objeto [Instant]
 * @property discontinuationDateRepresentation La representación de la fecha de discontinuación como un String
 * @property selectedCategory La categoria seleccionada para el producto.
 * @property selectedSection La [Section] seleccionada por el producto.
 * @property selectedDependency La [Dependency] seleccionada que afectará a las secciones mostradas en el
 * [app.features.productcreation.ui.base.composables.ExposedDropDownMenuForSection]
 * @property notes Las notas del producto.
 * @property tags La etiquetas del producto.
 */
data class InputDataState(
    val code : String = "",
    val name : String = "",
    val shortName : String = "",
    val description : String = "",
    val serieNumber : String = "",
    val modelCode : String = "",
    val productType : String = "",
    val stock : String = "",
    val uriImage : Uri? = null,
    val price : String = "",
    val minimunStock: String = "",
    val adquisitionDate : Instant? = null,
    val adquisitionDateRepresentation : String? = null,
    val discontinuationDate : Instant? = null,
    val discontinuationDateRepresentation : String? = null,
    val selectedCategory : Category? = null,
    val selectedSection : Section? = null,
    val selectedDependency : Dependency? = null,
    val notes : String = "",
    val tags : String = ""
)

/**
 * Un data class que almacena en variables indicativas de errores en los campos.
 *
 * @property shortNameError Cuando el campo del nombre corto es erroneo.
 * @property emptyFields Cuando hay campos obligatorios vacios.
 * @property priceError Cuando el campo del precio es erroneo.
 * @property stockError Cuando el campo del stock es erroneo.
 */
data class ErrorDataState(
    val shortNameError : Boolean = false,
    val emptyFields : Boolean = false,
    val priceError: Boolean = false,
    val stockError : Boolean = false
    )