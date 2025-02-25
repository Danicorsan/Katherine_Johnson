package app.features.productlist.ui.base.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.base.ui.Separations
import app.base.ui.composables.MediumTitleText
import app.base.ui.composables.SmallSpace
import app.domain.invoicing.product.Product
import app.features.productlist.ui.base.composable.CustomSpacerBetweenEachProduct
import app.features.productlist.ui.base.composable.DefaultProductImage
import app.features.productlist.ui.base.Specification
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.composable.PutInRowWithSeparation

@Composable
fun ListProducts(productList: List<Product>, productListEvents: ProductListEvents) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = Specification.BOTTOMCONTENTPADDINGFORPRODUCTLIST)
    ) {
        items(productList) {
            ProductInformationCard(
                modifier = Modifier
                    .fillMaxWidth(Specification.RELATIVEWITHDFORPRODUCTINFORMATIONCARD)
                    .padding(vertical = Separations.Small),
                product = it,
                onSeeProductDetails = productListEvents.seeProductDetails,
                onDeleteProduct = productListEvents.onDeleteProduct
            )
        }
        item {
            CustomSpacerBetweenEachProduct()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductInformationCard(
    modifier: Modifier = Modifier,
    product: Product,
    onSeeProductDetails: (Product) -> Unit,
    onDeleteProduct: (Product) -> Unit,
) {
    Card(
        modifier = modifier.combinedClickable(
            onClick = { onSeeProductDetails(product) },
            onLongClick = { onDeleteProduct(product) }
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Separations.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShowProductImage(product)
            ShowBasicInformation(product)
        }
    }
}

@Composable
private fun ShowProductImage(product: Product) {//Mantener parametro de cara a la implementación de imagenes
    Box(
        modifier = Modifier
            .padding(start = Separations.Medium)
    ) {
        DefaultProductImage()
    }
}

@Composable
private fun ShowBasicInformation(product: Product) {
    Column(
        modifier = Modifier.fillMaxWidth(),
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
private fun ShowProductsName(product: Product) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        MediumTitleText(product.name)
    }
}