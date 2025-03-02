package app.features.inventorylist.ui.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType

/**
 * Obtiene el icono para un InventoryIcon
 *
 * @param inventoryIcon Icono del inventario
 * @return El icono asociado al InventoryIcon
 */
fun getIconForInventoryIcon(inventoryIcon: InventoryIcon): ImageVector {
    return when (inventoryIcon) {
        InventoryIcon.ELECTRONICS -> Icons.Filled.Call
        InventoryIcon.TECHNOLOGY -> Icons.Filled.Settings
        InventoryIcon.MATERIALS -> Icons.Filled.ShoppingCart
        InventoryIcon.SERVICES -> Icons.Filled.Notifications
        InventoryIcon.WAREHOUSE -> Icons.Filled.Home
        InventoryIcon.NONE -> Icons.Filled.Warning
    }
}

/**
 * Componente que representa una tarjeta de inventario
 *
 * @param inventory inventario que se va a mostrar
 * @param onClick callback que se llama cuando se hace clic en la tarjeta
 * @param onLongClick callback que se llama cuando se hace clic largo en la tarjeta
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InventoryCard(
    inventory: Inventory,
    onClick: (Inventory) -> Unit,
    onLongClick: (Inventory) -> Unit
) {
    val icon = getIconForInventoryIcon(inventory.icon)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(inventory) }
            .combinedClickable(
                onClick = { onClick(inventory) },
                onLongClick = { onLongClick(inventory) }
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = inventory.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InventoryCardPreview() {
    InventoryCard(
        inventory = Inventory(
            id = 1,
            name = "Inventario 1",
            description = "Descripción del inventario 1",
            icon = InventoryIcon.ELECTRONICS,
            inventoryType = InventoryType.SEMESTRAL,
            shortName = "Inv1",
            state = InventoryState.ACTIVE,
            code = "INV-001",
        ),
        onClick = {},
        onLongClick = {},
    )
}