package app.features.productlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.composables.NormalButton
import app.features.productlist.R

data class ProductListEvents(
    val addProduct : () -> Unit,
    val seeProductDetails : (String) -> Unit
)

@Preview(showSystemUi = true)
@Composable
fun ProductListScreen(viewModel: ProductListViewModel = ProductListViewModel()){
    ProductListContent(
        productList = viewModel.products,
        productListEvents = ProductListEvents(
            addProduct = viewModel::addProduct,
            seeProductDetails = viewModel::seeProductDetails
        )
    )
}

@Composable
private fun ProductListContent(productList : List<String>, productListEvents: ProductListEvents){
    Column {
        Appbar(stringResource(R.string.titulo_appbar))
        AddProductButton(productListEvents.addProduct)
        ListedProducts(productList, productListEvents)
    }
}

@Composable
private fun ListedProducts(listaProductos: List<String>, productListEvents: ProductListEvents){
    LazyColumn {
        items(listaProductos) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {productListEvents.seeProductDetails(it)}
            ) {
                ShowProduct(it)
            }
        }
    }
}

@Composable
private fun AddProductButton(añadirProducto: () -> Unit){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        NormalButton(
            text = stringResource(R.string.texto_boton),
            onClick = añadirProducto
        )
    }
}