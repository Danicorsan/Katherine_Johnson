package app.features.productcreation.ui.base

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
    val onNewAcquisitionDateSelected : (Long?) -> Unit,
    val onNewDiscontinuationDateSelected : (Long?) -> Unit
    ){
    companion object{
        fun build(viewModel : ProductBaseCreationViewModel) : ProductEvents{
            return ProductEvents(
                onAcceptProduct = viewModel::onAcceptChanges,
                onLeavePage = viewModel::onLeavePage,
                onCodeChange = viewModel::onCodeChange,
                onNameChange = viewModel::onNameChange,
                onShortNameChange = viewModel::onShortNameChange,
                onDescriptionChange = viewModel::onDescriptionChange,
                onSerialNumberChange = viewModel::onSerialNumberChange,
                onModelCodeChange = viewModel::onModelCodeChange,
                onProductTypeChange = viewModel::onProductTypeChange,
                onStockChange = viewModel::onStockChange,
                onPriceChange = viewModel::onPriceChange,
                onMinimunStock = viewModel::onMinimunStockChange,
                onNewAcquisitionDateSelected = viewModel::onAcquisitonDateChange,
                onNewDiscontinuationDateSelected = viewModel::onDiscontinuationDateChange
            )
        }
    }
}