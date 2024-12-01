package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.features.inventorycreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInventoryScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.crear_inventario)) })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(stringResource(R.string.nombre_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(stringResource(R.string.descripci_n_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.crear_inventario))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateInventoryScreen() {
    CreateInventoryScreen()
}