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
fun Appbar(titleText : String, navigationAction : () -> Unit = {}){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = navigationAction) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun ShowProduct (product : String){
    Text(
        product,
        modifier = Modifier.padding(Separations.Small)
    )
}