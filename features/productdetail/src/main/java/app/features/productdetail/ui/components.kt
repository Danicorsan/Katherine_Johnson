package app.features.productdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.SmallSpace
import app.domain.invoicing.product.ProductState
import app.features.productdetail.R

@Composable
fun ProductDetailsColumn(modifier: Modifier = Modifier, dataType : String, data : String?){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextTag(text = dataType)
        SmallSpace()
        data?.let {
            InformativeText(text = data)
        } ?: InformativeText(text = stringResource(R.string.field_without_value))
    }
}

@Composable
fun ProductDetailsColumn(modifier: Modifier = Modifier, dataType : String, state : ProductState){
    val textoMostrar = when(state){
        ProductState.new -> stringResource(R.string.state_as_new)
        ProductState.modified -> stringResource(R.string.state_as_modified)
        ProductState.verified -> stringResource(R.string.state_as_verified)
    }

    ProductDetailsColumn(dataType = dataType, data = textoMostrar)
}

@Composable
fun ProductDetailsColumn(modifier: Modifier = Modifier, dataType : String, content : @Composable () -> Unit){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextTag(text = dataType)
        SmallSpace()
        content.invoke()
    }
}

@Composable
fun MakeInRow(
    productDetail1 : @Composable () -> Unit,
    productDetail2: @Composable () -> Unit
){
    Row(
    ) {
        productDetail1()
        LargeSpace()
        productDetail2()
    }
}