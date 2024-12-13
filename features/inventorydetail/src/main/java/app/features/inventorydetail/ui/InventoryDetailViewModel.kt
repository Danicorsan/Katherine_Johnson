package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import app.domain.invoicing.inventory.Item

open class InventoryDetailViewModel : ViewModel() {
    val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> = _uiState

    open fun loadInventoryDetails() {
        // Simulación de carga de datos
        val sampleItems = listOf(
            Item(1, "Camiseta Roja", "Camiseta de algodón, talla M"),
            Item(2, "Pantalones Azules", "Pantalones de mezclilla, talla 32"),
            Item(3, "Zapatos Negros", "Zapatos de cuero, talla 42")
        )
        // Actualizamos el estado con los artículos
        _uiState.value = InventoryDetailState(items = sampleItems)
    }
}
