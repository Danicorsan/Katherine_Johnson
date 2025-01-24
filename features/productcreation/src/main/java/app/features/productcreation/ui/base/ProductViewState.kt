package app.features.productcreation.ui.base

import app.domain.invoicing.category.Category
import kotlinx.datetime.Instant

data class ProductViewState(
    val inputDataState: InputDataState = InputDataState(),
    val errorDataState: ErrorDataState = ErrorDataState(),
    val isLoading : Boolean = false,
    val categoriesList : Iterable<Category> = emptyList(),
    val productRegisterSuccessful : Boolean = false,
    val sectionsList : Iterable<String> = emptyList(),
    val productIsBeingAdded : Boolean = false
)

data class InputDataState(
    val code : String = "",
    val name : String = "",
    val shortName : String = "",
    val description : String = "",
    val serieNumber : String = "",
    val modelCode : String = "",
    val productType : String = "",
    val stock : String = "",
    val price : String = "",
    val minimunStock: String = "",
    val adquisitionDate : Instant? = null,
    val adquisitionDateRepresentation : String? = null,
    val discontinuationDate : Instant? = null,
    val discontinuationDateRepresentation : String? = null,
    val selectedCategory : Category? = null,
    val selectedSection : String? = null,
    val notes : String = "",
    val tags : String = ""
)

data class ErrorDataState(
    val shortNameError : Boolean = false,
    val cantRegisterProduct : Boolean = false,
    val emptyFields : Boolean = false,
    val priceError: Boolean = false,
    val stockError : Boolean = false
    )