package app.features.productcreation.ui.base.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.productcreation.R
import app.features.productcreation.ui.base.Specification

/**
 * Crea un mismo elemento [BaseAppBar] tanto para creación
 * como edición de productos
 *
 * @param titleText El título mostrado por el elemento [BaseAppBar]
 * @param onLeavePage El evento al presionar el elemento [BaseAppBarState.navigationIcon]
 * @receiver
 */
@Composable
fun ProductCreationAppbar(titleText : String, onLeavePage : () -> Unit){
    BaseAppBar(BaseAppBarState(
        title = titleText,
        navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon(onClick = onLeavePage)
    ))
}

/**
 * Crea un mismo elemento [MediumButton] para usar un mísmo botón flotante
 * para edición y creación de productos.
 *
 * @param onCreateProduct El evento a realizar al presionar el botón.
 * @receiver
 */
@Composable
fun ProductCreationFloatingActionButton(onCreateProduct : () -> Unit){
    MediumButton(
        onClick = onCreateProduct,
        imageVector = Specification.FLOATINGACTIONBUTTONICON,
        contentDescription = stringResource(R.string.on_create_product_icon)
    )
}
