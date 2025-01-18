package app.features.productlist.ui.base.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.MediumTitleText
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.domain.invoicing.product.Product
import app.features.productlist.R
import app.features.productlist.ui.base.composable.CustomSpacerBetweenEachProduct
import app.features.productlist.ui.base.composable.DefaultProductImage
import app.features.productlist.ui.base.Specification
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.composable.PutInRowWithSeparation

@Composable
fun ListProducts(productList: List<Product>, productListEvents: ProductListEvents){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = Specification.BOTTOMPADDINGVALUETOCONTENTPADDINGFORPRODUCTLIST)
    ){
        items(productList) {
            ShowProductTile(it, productListEvents)
        }
        item {
            CustomSpacerBetweenEachProduct()
        }
    }
}

@Composable
fun ShowProductTile (product : Product, productListEvents : ProductListEvents){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Separations.Small),
        onClick = {productListEvents.seeProductDetails(product)},

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Separations.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShowProductImage(product)
            ShowBasicInformation(product)
            ShowIconActions(product, productListEvents)
        }
    }
}

@Composable
private fun ShowProductImage(product: Product){//Mantener parametro de cara a la implementación de imagenes
    Box(
        modifier = Modifier
            .padding(start = Separations.Small)
    ){
        DefaultProductImage()
    }
}

@Composable
private fun ShowBasicInformation(product : Product){
    Column(
        modifier = Modifier.fillMaxWidth(Specification.RELATIVESPACEFORPRODUCTBASICINFORMATION),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowProductsName(product)
        SmallSpace()
        PutInRowWithSeparation(
            { Text(product.code) },
            { Text(product.serialNumber) }
        )
        SmallSpace()
        PutInRowWithSeparation(
            { Text(product.productType) },
            { Text(product.stock.toString()) }
        )
    }
}

@Composable
private fun ShowProductsName(product: Product){
    Box(
        contentAlignment = Alignment.Center
    ){
        MediumTitleText(product.name)
    }
}

@Composable
private fun ShowIconActions(
    product: Product,
    productListEvents : ProductListEvents
){
    PutInRowWithSeparation(
        {
            IconButton(
                onClick = {productListEvents.onEditProduct(product)}
            ) {
                Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.edit_product_iconbutton))
            }
        },
        {
            IconButton(
                onClick = {productListEvents.onDeleteProduct(product)}
            ) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete_iconbutton_description))
            }
        }
    )
}