package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.features.inventorydetail.R

data class Item(val name: String, val description: String)

@Composable
fun InventoryDetailScreen(items: List<Item>, onBackClick: () -> Unit) {
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
                contentDescription = stringResource(R.string.volver),
            )
        }

        // Título de la pantalla
        Text(
            text = stringResource(R.string.detalle_del_inventario),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        // Lista de artículos dentro del inventario
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                ItemCard(item = item)
            }
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    letterSpacing = 0.5.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    letterSpacing = 0.5.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryDetailScreenPreview() {
    val items = listOf(
        Item("Camiseta Roja", "Camiseta de algodón, talla M"),
        Item("Pantalones Azules", "Pantalones de mezclilla, talla 32"),
        Item("Zapatos Negros", "Zapatos de cuero, talla 42")
    )

    InventoryDetailScreen(items = items, onBackClick = {})
}
