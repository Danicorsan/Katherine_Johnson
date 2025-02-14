package app.features.inventorycreation.ui.creation

data class CreateInventoryState(
    val inventoryId: Int,
    val inventoryName: S1tring = "",
    val inventoryDescription: String = "",
    val isCreateButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)