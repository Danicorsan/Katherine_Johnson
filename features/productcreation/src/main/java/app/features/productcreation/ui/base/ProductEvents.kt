package app.features.productcreation.ui.base

import app.domain.invoicing.category.Category
import app.domain.invoicing.section.Section

/**
 * Data class que recoge todos los eventos para las pantallas de creación y edición de un producto.
 *
 * @property onAcceptProduct Cuando el usuario quiere guardar los cambios.
 * @property onLeavePage Cuando el usuario se quiere ir de la pagina sin guardar los cambios.
 * @property onCodeChange Cuando el codigo del producto se modifica.
 * @property onNameChange Cuando el nombre del producto se modifica.
 * @property onShortNameChange Cuando el nombre corto del producto se modifica.
 * @property onDescriptionChange Cuando la descripción del producto se modifica.
 * @property onSerialNumberChange Cuando el número de serie del producto se modifica.
 * @property onModelCodeChange Cuando el código de modelo del producto se modifica.
 * @property onProductTypeChange Cuando el tipo del producto se modifica.
 * @property onStockChange Cuando el stock del producto se modifica.
 * @property onPriceChange Cuando el precio del producto se modifica.
 * @property onMinimunStock Cuando el mínimo del stock de producto se modifica (Todavia no está siendo usado).
 * @property onNotesChanged Cuando las notas del producto se modifican.
 * @property onTagsChanged Cuando las etiquetas del producto se modifican.
 * @property onNewAcquisitionDateSelected Cuando la fecha de adquisición del producto se modifican.
 * @property onNewDiscontinuationDateSelected Cuando la fecha de discontinuación del producto se modifica.
 * @property onNewCategorySelected Cuando la [Category] del producto se modifica.
 * @property onNewSectionSelected Cuando la [Section] del producto se modifica.
 * @property onDismissCantRegisterProductAlertDialog Cuando el usuario ha leido y acepta que no se ha podido registrar los cambios
 * @property onDismissEmptyFieldsAlertDialog Cuando el usuario ha leido y acepta que hay campos vacios.
 * @property onDismissProductHasBeenRegisteredAlertDialog Cuando el usuario ha leido y acepta que los cambios se han guardado.
 * @constructor Create empty Product events
 */
data class ProductEvents(
    val onAcceptProduct : () -> Unit,
    val onLeavePage : () -> Unit,
    val onCodeChange : (String) -> Unit,
    val onNameChange : (String) -> Unit,
    val onShortNameChange : (String) -> Unit,
    val onDescriptionChange : (String) -> Unit,
    val onSerialNumberChange : (String) -> Unit,
    val onModelCodeChange : (String) -> Unit,
    val onProductTypeChange : (String) -> Unit,
    val onStockChange : (String) -> Unit,
    val onPriceChange : (String) -> Unit,
    val onMinimunStock : (String) -> Unit,
    val onNotesChanged : (String) -> Unit,
    val onTagsChanged : (String) -> Unit,
    val onNewAcquisitionDateSelected : (Long?) -> Unit,
    val onNewDiscontinuationDateSelected : (Long?) -> Unit,
    val onNewCategorySelected : (Category) -> Unit,
    val onNewSectionSelected : (Section) -> Unit,
    val onDismissCantRegisterProductAlertDialog : () -> Unit,
    val onDismissEmptyFieldsAlertDialog : () -> Unit,
    val onDismissProductHasBeenRegisteredAlertDialog : () -> Unit
    ){
    companion object{
        /**
         * Permite construir a partir de un [ProductBaseCreationViewModel] un objeto
         * [ProductEvents] en el que se initicalizan todos los campos.
         *
         * @param viewModel El viewModel del cual obtener los eventos a realizar.
         * @return Un [ProductEvents] con todas sus propiedades inicializadas.
         */
        fun build(viewModel : ProductBaseCreationViewModel) : ProductEvents{
            return ProductEvents(
                onAcceptProduct = viewModel::onAcceptChanges,
                onLeavePage = viewModel::onLeavePage,
                onCodeChange = viewModel::onCodeChanged,
                onNameChange = viewModel::onNameChanged,
                onShortNameChange = viewModel::onShortNameChanged,
                onDescriptionChange = viewModel::onDescriptionChanged,
                onSerialNumberChange = viewModel::onSerialNumberChanged,
                onModelCodeChange = viewModel::onModelCodeChanged,
                onProductTypeChange = viewModel::onProductTypeChanged,
                onStockChange = viewModel::onStockChange,
                onPriceChange = viewModel::onPriceChanged,
                onMinimunStock = viewModel::onMinimunStockChanged,
                onNotesChanged = viewModel::onNotesChanged,
                onTagsChanged = viewModel::onTagsChanged,
                onNewAcquisitionDateSelected = viewModel::onAcquisitonDateChanged,
                onNewDiscontinuationDateSelected = viewModel::onDiscontinuationDateChanged,
                onNewCategorySelected = viewModel::onNewCategorySelected,
                onNewSectionSelected = viewModel::onNewSectionSelected,
                onDismissCantRegisterProductAlertDialog = viewModel::onDismissCantRegisterAlertDialog,
                onDismissEmptyFieldsAlertDialog = viewModel::onDismissEmptyFieldsAlertDialog,
                onDismissProductHasBeenRegisteredAlertDialog = viewModel:: onDismissProductHasBeenRegisteredAlertDialog
            )
        }
    }
}