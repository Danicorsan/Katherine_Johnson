package app.features.productlist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.base.ui.composables.Separations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar( titleText : String, accionNavegacion : () -> Unit = {}){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = accionNavegacion) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun MostrarProducto (producto : String){
    Text(
        producto,
        modifier = Modifier.padding(Separations.Small)
    )
}