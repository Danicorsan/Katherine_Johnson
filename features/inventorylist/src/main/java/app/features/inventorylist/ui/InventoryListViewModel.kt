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

    private val _inventories = mutableListOf<Inventory>()
    private val _uiState = mutableStateOf(InventoryListState(
        success = _inventories,
        loading = false,
        error = null,
        noData = false
    ))
    val uiState: InventoryListState get() = _uiState.value

    init {
        loadInventories()
    }

    private fun loadInventories() {
        viewModelScope.launch {
            _uiState.value = uiState.copy(loading = true)
            delay(1000)
            _inventories.clear()
            _inventories.addAll(repository.getAllInventories())
            _uiState.value = uiState.copy(success = _inventories, loading = false)
        }
    }
    fun onOpenDrawer(scope : CoroutineScope){
        scope.launch {
            uiState.drawerState.open()
        }
    }
    private var sortByDesc = false
    fun onSortByDesc() {
        sortByDesc = !sortByDesc
        _inventories.sortBy { it.createdAt }
        if (sortByDesc) {
            _inventories.reverse()
        }
    }
}