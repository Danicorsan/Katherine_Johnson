package app.features.productcreation.ui.base.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.features.productcreation.R
import app.features.productcreation.ui.base.Specification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCreationAppbar(titleText : String, onLeavePage : () -> Unit){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = onLeavePage) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun ProductCreationFloatingActionButton(onCreateProduct : () -> Unit){
    FloatingActionButton(
        onClick = onCreateProduct,
    ){
        Icon(
            imageVector = Specification.FLOATINGACTIONBUTTONICON,
            contentDescription = stringResource(R.string.on_create_product_icon)
        )
    }
}
