package app.features.productlist.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.SmallSpace
import app.features.productlist.R


@Composable
fun DefaultProductImage() {
        Image(
            painter = painterResource(R.drawable.product_default),
            contentDescription = stringResource(R.string.product_image_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize(InternalSpecification.RelativeImageSize)
                .clip(RoundedCornerShape(InternalSpecification.RelativeRoundedCornerShape))
        )
}

@Composable
fun PutInRowWithSeparation(
    composable1 : @Composable () -> Unit,
    composable2 : @Composable () -> Unit
){
    Row {
        composable1()
        SmallSpace()
        composable2()
    }
}

@Composable
fun AddProductFloatingActionButton(productListEvents: ProductListEvents){
    LargeFloatingActionButton (
        onClick = productListEvents.onAddProduct,
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_product_floatingbutton_description))
    }
}

@Composable
fun CustomSpacerForEdingOfProductList(){
    Spacer(modifier = Modifier.fillMaxHeight(InternalSpecification.RelativeSpaceForCustomSpacerForLazyColumn))
}