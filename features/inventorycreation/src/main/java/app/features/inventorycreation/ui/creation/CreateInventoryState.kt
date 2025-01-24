package app.features.inventorycreation.ui.creation

data class CreateInventoryState(
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val isCreateButtonEnabled: Boolean = false,
    val isLoading: Boolean = false, // Para controlar el estado de carga
    val errorMessage: String? = null // Para manejar posibles errores
)