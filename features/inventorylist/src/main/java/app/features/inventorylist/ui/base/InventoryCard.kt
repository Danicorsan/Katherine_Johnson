package app.features.inventorylist.ui.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Inventory
import app.features.inventorylist.R

@Composable
fun InventoryCard(
    inventory: Inventory,
    onClick: (Inventory) -> Unit,
    onEditClick: (Inventory) -> Unit,
    onDeleteClick: (Inventory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(inventory) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.almacen1), // Puedes usar cualquier recurso aquí
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = inventory.description, style = MaterialTheme.typography.bodySmall)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(onClick = { onEditClick(inventory) }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = stringResource(R.string.editar_inventario))
                    }
                    IconButton(onClick = { onDeleteClick(inventory) }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(
                            R.string.eliminar_inventario)
                        )
                    }
                }
            }
        }
    }
}