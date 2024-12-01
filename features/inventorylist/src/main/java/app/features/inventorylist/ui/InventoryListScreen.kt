package app.features.inventorylist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Inventory(val name: String, val quantity: Int, val price: Double)

@Composable
fun InventoryListScreen(inventories: List<Inventory>, onBackClick: () -> Unit, onInventoryClick: (Inventory) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Icono de retroceso
        IconButton(
            onClick = { onBackClick() },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
            )
        }

        // Título de la pantalla
        Text(
            text = "Lista de Inventarios",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        // Lista de inventarios
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(inventories) { inventory ->
                InventoryCard(inventory = inventory, onClick = { onInventoryClick(inventory) })
            }
        }
    }
}

@Composable
fun InventoryCard(inventory: Inventory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        letterSpacing = 0.5.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cantidad: ${inventory.quantity}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        letterSpacing = 0.5.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${inventory.price}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryListScreenPreview() {
    val inventories = listOf(
        Inventory("Inventario 1", 20, 99.99),
        Inventory("Inventario 2", 15, 149.99),
        Inventory("Inventario 3", 30, 199.99)
    )

    InventoryListScreen(inventories = inventories, onBackClick = {}, onInventoryClick = { inventory ->
        // Lógica para manejar la selección de un inventario
    })
}
