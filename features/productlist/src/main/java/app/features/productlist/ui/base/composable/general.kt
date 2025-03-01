package app.features.productlist.ui.base.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.base.ui.composables.SmallSpace
import app.features.productlist.ui.base.Specification

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