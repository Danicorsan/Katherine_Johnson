package app.features.productlist.ui.base.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.SmallSpace
import app.features.productlist.R
import app.features.productlist.ui.base.Specification

/**
 * Permite obtener la imagen por defecto a ser mostrada.
 *
 */
@Composable
fun DefaultProductImage() {
    Image(
        painter = painterResource(R.drawable.product_default),
        contentDescription = stringResource(R.string.product_image_description),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxSize(Specification.RELATIVEIMAGESIZE)
            .clip(RoundedCornerShape(Specification.RELATIVEROUNDEDCORNERSHAPE))
    )
}

/**
 * Permite poner dos funciones composables en un [Row] con una misma separación.
 *
 * @param composable1 Elemento a la izquierda o antes del [composable2].
 * @param composable2 Elemento a la derecha o despues del [composable1].
 */
@Composable
fun PutInRowWithSeparation(
    composable1: @Composable () -> Unit,
    composable2: @Composable () -> Unit
) {
    Row {
        composable1()
        SmallSpace()
        composable2()
    }
}

@Composable
fun CustomSpacerBetweenEachProduct() {
    Spacer(modifier = Modifier.fillMaxHeight(Specification.RELATIVESPACEFORCUSTOMSPACERFORPRODUCTLIST))
}