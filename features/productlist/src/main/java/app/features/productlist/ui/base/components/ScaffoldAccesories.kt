package app.features.productlist.ui.base.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.features.productlist.R
import app.features.productlist.ui.base.ProductListEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListAppBar(
    titleText : String,
    onLeavePage : () -> Unit){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = onLeavePage) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.go_back_iconbutton))
            }
        }
    )
}

@Composable
fun AddProductFloatingActionButton(productListEvents: ProductListEvents){
    FloatingActionButton(
        onClick = productListEvents.onAddProduct,
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_product_floatingbutton_description))
    }
}

