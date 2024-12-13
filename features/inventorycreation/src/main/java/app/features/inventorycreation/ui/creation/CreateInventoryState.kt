package app.features.inventorycreation.ui.creation

data class CreateInventoryState(
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val isCreateButtonEnabled: Boolean = false
)