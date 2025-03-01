package app.features.productcreation.ui.base

import android.net.Uri
import app.domain.invoicing.category.Category
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.section.Section

/**
 * Data class que recoge todos los eventos para las pantallas de creación y edición de un producto.
 *
 * @property onAcceptProduct Cuando el usuario quiere guardar los cambios.
 * @property onLeavePage Cuando el usuario se quiere ir de la pagina sin guardar los cambios.
 * @property onNewImageSelected Cuando el usuario selecciona una imagen, pasa por parametro su [Uri]
 * @property onCodeChange Cuando el codigo del producto se modifica.
 * @property onNameChange Cuando el nombre del producto se modifica.
 * @property onShortNameChange Cuando el nombre corto del producto se modifica.
 * @property onDescriptionChange Cuando la descripción del producto se modifica.
 * @property onSerialNumberChange Cuando el número de serie del producto se modifica.
 * @property onModelCodeChange Cuando el código de modelo del producto se modifica.
 * @property onProductTypeChange Cuando el tipo del producto se modifica.
 * @property onStockChange Cuando el stock del producto se modifica.
 * @property onPriceChange Cuando el precio del producto se modifica.
 * @property onMinimunStockChanged Cuando el mínimo del stock de producto se modifica.
 * @property onNotesChanged Cuando las notas del producto se modifican.
 * @property onTagsChanged Cuando las etiquetas del producto se modifican.
 * @property onNewAcquisitionDateSelected Cuando la fecha de adquisición del producto se modifican.
 * @property onNewDiscontinuationDateSelected Cuando la fecha de discontinuación del producto se modifica.
 * @property onNewCategorySelected Cuando la [Category] del producto se modifica.
 * @property onNewSectionSelected Cuando la [Section] del producto se modifica.
 * @property onNewDependencySelected Cuando la [Dependency] selecionada en el formulario cambia.
 * @property onDismissCantRegisterProductAlertDialog Cuando el usuario ha leido y acepta que no se ha podido registrar los cambios
 * @property onDismissEmptyFieldsAlertDialog Cuando el usuario ha leido y acepta que hay campos vacios.
 * @constructor Create empty Product events
 */
data class ProductEvents(
    val onAcceptProduct: () -> Unit,
    val onLeavePage: () -> Unit,
    val onNewImageSelected: (Uri) -> Unit,
    val onCodeChange: (String) -> Unit,
    val onNameChange: (String) -> Unit,
    val onShortNameChange: (String) -> Unit,
    val onDescriptionChange: (String) -> Unit,
    val onSerialNumberChange: (String) -> Unit,
    val onModelCodeChange: (String) -> Unit,
    val onProductTypeChange: (String) -> Unit,
    val onStockChange: (String) -> Unit,
    val onPriceChange: (String) -> Unit,
    val onMinimunStockChanged: (String) -> Unit,
    val onNotesChanged: (String) -> Unit,
    val onTagsChanged: (String) -> Unit,
    val onNewAcquisitionDateSelected: (Long?) -> Unit,
    val onNewDiscontinuationDateSelected: (Long?) -> Unit,
    val onNewCategorySelected: (Category) -> Unit,
    val onNewSectionSelected: (Section) -> Unit,
    val onNewDependencySelected: (Dependency) -> Unit,
    val onDismissCantRegisterProductAlertDialog: () -> Unit,
    val onDismissEmptyFieldsAlertDialog: () -> Unit
) {
    constructor(viewModel: ProductBaseCreationViewModel) : this(
        onAcceptProduct = viewModel::onAcceptChanges,
        onLeavePage = viewModel::onLeavePage,
        onNewImageSelected = viewModel::onNewImageSelected,
        onCodeChange = viewModel::onCodeChanged,
        onNameChange = viewModel::onNameChanged,
        onShortNameChange = viewModel::onShortNameChanged,
        onDescriptionChange = viewModel::onDescriptionChanged,
        onSerialNumberChange = viewModel::onSerialNumberChanged,
        onModelCodeChange = viewModel::onModelCodeChanged,
        onProductTypeChange = viewModel::onProductTypeChanged,
        onStockChange = viewModel::onStockChanged,
        onPriceChange = viewModel::onPriceChanged,
        onMinimunStockChanged = viewModel::onMinimunStockChanged,
        onNotesChanged = viewModel::onNotesChanged,
        onTagsChanged = viewModel::onTagsChanged,
        onNewAcquisitionDateSelected = viewModel::onAcquisitonDateChanged,
        onNewDiscontinuationDateSelected = viewModel::onDiscontinuationDateChanged,
        onNewCategorySelected = viewModel::onNewCategorySelected,
        onNewSectionSelected = viewModel::onNewSectionSelected,
        onNewDependencySelected = viewModel::onNewDependencySelected,
        onDismissCantRegisterProductAlertDialog = viewModel::onDismissCantRegisterAlertDialog,
        onDismissEmptyFieldsAlertDialog = viewModel::onDismissEmptyFieldsAlertDialog
    )
}