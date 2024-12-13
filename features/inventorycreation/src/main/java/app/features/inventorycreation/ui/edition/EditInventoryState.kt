package app.features.inventorycreation.ui.edition

data class EditInventoryState(
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val isSaveButtonEnabled: Boolean = false
)
