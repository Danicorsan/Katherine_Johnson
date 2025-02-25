package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryDetailViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    fun loadInventoryDetails(inventoryId: Int) {
        viewModelScope.launch {
            val inventory = repository.getInventoryById(inventoryId)
            if (inventory != null) {
                _uiState.value = _uiState.value.copy(
                    inventory = inventory
                )
            }
        }
    }
}