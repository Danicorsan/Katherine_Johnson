package app.features.productcreation.ui.base.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.productcreation.R
import app.features.productcreation.ui.base.Specification

@Composable
fun ProductCreationAppbar(titleText : String, onLeavePage : () -> Unit){
    BaseAppBar(BaseAppBarState(
        title = titleText,
        navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon(onClick = onLeavePage)
    ))
}

@Composable
fun ProductCreationFloatingActionButton(onCreateProduct : () -> Unit){
    MediumButton(
        onClick = onCreateProduct,
        imageVector = Specification.FLOATINGACTIONBUTTONICON,
        contentDescription = stringResource(R.string.on_create_product_icon)
    )
}
