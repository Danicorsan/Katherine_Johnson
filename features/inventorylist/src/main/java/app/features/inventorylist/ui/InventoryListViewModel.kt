package app.features.inventorylist.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryListViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(
        InventoryListState(
            success = emptyList(),
            loading = false,
            error = null,
            noData = false,
        )
    )
    val uiState: InventoryListState get() = _uiState.value

    init {
        loadInventories()
    }

    private fun loadInventories() {
        viewModelScope.launch {
            _uiState.value = uiState.copy(loading = true)
            try {
                delay(1000)
                val inventories = repository.getAllInventories()
                _uiState.value = uiState.copy(
                    success = inventories,
                    loading = false,
                    noData = inventories.isEmpty()
                )
            } catch (e: Exception) {
                _uiState.value = uiState.copy(
                    error = "Error al cargar los inventarios: ${e.message}",
                    loading = false
                )
            }
        }
    }

    fun onOpenDrawer(scope: CoroutineScope) {
        scope.launch {
            uiState.drawerState.open()
        }
    }

    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(loading = true)
            try {
                val success = repository.deleteInventory(inventory.id)
                if (success) {
                    loadInventories()
                } else {
                    _uiState.value = uiState.copy(
                        error = "No se pudo eliminar el inventario",
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = uiState.copy(
                    error = "Error al eliminar el inventario: ${e.message}",
                    loading = false
                )
            }
        }
    }

    fun refreshInventories() {
        loadInventories()
    }
}
