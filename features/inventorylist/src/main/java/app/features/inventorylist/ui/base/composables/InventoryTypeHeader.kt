package app.features.inventorylist.ui.base.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.InventoryState
import app.features.inventorylist.R

@Composable
fun InventoryTypeHeader(type: InventoryState) {
    val typeNameResId = when (type) {
        InventoryState.ACTIVE -> R.string.activo
        InventoryState.HISTORY -> R.string.historico
        InventoryState.IN_PROGRESS -> R.string.en_proceso
    }

    Text(
        text = stringResource(typeNameResId),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    )
}