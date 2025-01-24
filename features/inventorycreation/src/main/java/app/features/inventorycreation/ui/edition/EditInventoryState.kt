package app.features.inventorycreation.ui.edition

data class EditInventoryState(
    val inventoryId: Int = 0,
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val isSaveButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
