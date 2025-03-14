package app.features.productlist.ui.base.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import app.base.ui.Separations
import app.base.ui.composables.MediumTitleText
import app.base.ui.composables.SmallSpace
import app.domain.invoicing.product.Product
import app.features.productlist.R
import app.features.productlist.ui.base.composable.CustomSpacerBetweenEachProduct
import app.features.productlist.ui.base.Specification
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.composable.PutInRowWithSeparation
import coil.compose.rememberAsyncImagePainter

/**
 * Permite listar todos los productos pasados por parametros a traves
 * de un [LazyColumn].
 *
 * @param productList La lista de productos que pueden ser mostrados.
 * @param productListEvents Un objeto [ProductListEvents] para recoger los eventos que necesiten
 * usarse en listado.
 */
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
private fun ShowProductImage(product: Product) {
    Box(
        modifier = Modifier
            .padding(start = Separations.Medium)
    ) {
        if (product.image == null)
            Image(
                painter = painterResource(R.drawable.product_default),
                contentDescription = stringResource(R.string.product_image_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize(Specification.RELATIVEIMAGESIZE)
                    .clip(RoundedCornerShape(Specification.RELATIVEROUNDEDCORNERSHAPE))
            )
        else
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = stringResource(R.string.product_image_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize(Specification.RELATIVEIMAGESIZE)
                    .clip(RoundedCornerShape(Specification.RELATIVEROUNDEDCORNERSHAPE))
            )
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