package app.features.inventorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class InventoryListViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _inventories = mutableListOf<Inventory>() // Lista de inventarios local
    val inventories: MutableList<Inventory> get() = _inventories // Getter para la lista de inventarios

    private val _uiState = mutableStateOf(InventoryListState(
        inventories = _inventories,
        isLoading = false,
        errorMessage = null
    ))
    val uiState: InventoryListState get() = _uiState.value

    var state: InventoryListState by mutableStateOf(InventoryListState(
        inventories = _inventories,
        isLoading = false,
        errorMessage = null
    ))
        private set

    init {
        loadInventoriesWithDelay() // Cargar inventarios con delay cuando se inicie el ViewModel
    }

    // Cargar los inventarios con un retraso simulado
    private fun loadInventoriesWithDelay() {
        viewModelScope.launch {
            // Primero mostrar LoadingUI
            _uiState.value = uiState.copy(isLoading = true)

            // Esperar 3 segundos
            delay(3000)

            // Luego cargar los inventarios
            loadInventories()

            // Después de cargar los inventarios, poner isLoading = false
            _uiState.value = uiState.copy(isLoading = false)
        }
    }

    // Cargar los inventarios desde el repositorio
    private fun loadInventories() {
        viewModelScope.launch {
            _inventories.clear()
            _inventories.addAll(inventoryRepository.getAllInventories())
            _uiState.value = uiState.copy(inventories = _inventories, isLoading = false)
        }
    }

    // Función para eliminar un inventario
    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(isLoading = true)
            val success = inventoryRepository.deleteInventory(inventory.id)
            if (success) {
                loadInventories()
            } else {
                _uiState.value = uiState.copy(errorMessage = "Error al eliminar el inventario", isLoading = false)
            }
        }
    }

    // Función para agregar un inventario
    fun addInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(isLoading = true)
            val success = inventoryRepository.addInventory(inventory)
            if (success) {
                _inventories.add(inventory) // Agregar a la lista local
                _uiState.value = uiState.copy(inventories = _inventories, isLoading = false)
            } else {
                _uiState.value = uiState.copy(errorMessage = "Error al agregar el inventario", isLoading = false)
            }
        }
    }
}
