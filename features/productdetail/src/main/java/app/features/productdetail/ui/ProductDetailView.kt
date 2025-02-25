package app.features.productdetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.features.productdetail.R
import app.features.productdetail.ui.base.ProductDetailsEvents
import app.features.productdetail.ui.base.ProductDetailsState
import app.features.productdetail.ui.base.components.ProductReadOnlyForm
import kotlinx.datetime.Instant
import java.util.Date

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailsViewModel,
    productId: Int,
    onGoBackNav: () -> Unit,
    onEditProductNav : (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadDataAndStablishNavigationEvent(productId, onGoBackNav, onEditProductNav)
    }
    ProductDetailsHost(
        productDetailsState = viewModel.productDetailsState,
        productDetailsEvents = ProductDetailsEvents(viewModel)
    )
}

@Composable
private fun ProductDetailsHost(
    productDetailsState: ProductDetailsState,
    productDetailsEvents: ProductDetailsEvents
) {
    Scaffold(
        topBar = {
            BaseAppBar(BaseAppBarState(
                title = stringResource(R.string.title_appbar_product_details),
                navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon(
                    onClick = productDetailsEvents.onGoBack
                ),
                listOf(
                    BaseAppBarIcons.editElementVisibleIcon(
                        typeElement = stringResource(R.string.action_icons_type_element),
                        onClick = productDetailsEvents.onEditProduct
                    ),
                    BaseAppBarIcons.deleteElementVisibleIcon(
                        typeElement = stringResource(R.string.action_icons_type_element),
                        onClick = productDetailsEvents.onDeleteProduct
                    )
                )
            ))
        }
    ) { contentPadding ->
        when {
            productDetailsState.product == null -> LoadingUi()
            productDetailsState.productBeingDeleted -> BaseAlertDialog(
                title = stringResource(R.string.alert_dialog_delete_title),
                text = stringResource(R.string.alert_dialog_delete_message),
                confirmText = stringResource(R.string.alert_dialog_on_confirm_button),
                dismissText = stringResource(R.string.alert_dialog_on_cancel_button),
                onConfirm = productDetailsEvents.onConfirmDeleteProduct,
                onDismiss = productDetailsEvents.onDismissDeleteProduct
            )
            else -> ProductDetailContent(
                modifier = Modifier.padding(contentPadding),
                product = productDetailsState.product
            )
        }
    }
}

@Composable
private fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: Product
) {
    Box(
        modifier = modifier
    ) {
        ProductReadOnlyForm(product = product)
    }
}

@Preview
@Composable
private fun ProductDetailPreview() {
    val category = Category(
        id = 3,
        name = "Nombre Categoria",
        shortName = "cat",
        description = "",
        image = "Una imagen",
        createdAt = Date(342422424),
        fungible = false
    )
    val section = "Nombre Seccion"
    val product = Product(
        code = "dependenciaSeccion3",
        name = "Esponja duradera max",
        shortName = "Esponja",
        description = "Algo descripcionfffffffffffffffffffffffffffffffffffffffff",
        serialNumber = "424D4234CD",
        modelCode = "97878DFWEFw",
        productType = "Aseo e Higiene",
        category = category,
        section = section,
        state = ProductState.new,
        stock = 32u,
        price = 23.44,
        acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
        notes = "Algunas notas de prueba\n¿Que más?"
    )
    ProductDetailContent(
        product = product,
    )
}