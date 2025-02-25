package app.features.productdetail.ui.base.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.HighSpace
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.base.utils.format
import app.base.utils.toCurrency
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.features.productdetail.R
import app.features.productdetail.ui.base.composables.DetailsProductScreenTitle
import app.features.productdetail.ui.base.composables.NormalTextBox
import app.features.productdetail.ui.base.composables.LargeTextBox
import app.features.productdetail.ui.base.composables.DetailsHead

@Composable
fun ProductReadOnlyForm(product: Product){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = Separations.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        DetailsProductScreenTitle(product.name)
        MediumSpace()
        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.code_label), data = product.code
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.short_name_label), data = product.shortName
            )
            }
        )
        MediumSpace()
        SeparatedRow(
            { ProductDetailsStamp(
                dataType = stringResource(R.string.category_label), data = product.category.name
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.serial_number_label), data = product.serialNumber
            )
            }
        )

        MediumSpace()
        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.model_code_label), data = product.modelCode
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.product_type_label), data = product.productType
            )
            }
        )
        MediumSpace()

        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.section_label), data = product.section
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.state_label), state = product.state
            )
            }
        )
        MediumSpace()
        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.stock_label), data = product.stock.toString()
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.price_label), data = product.price.toCurrency()
            )
            }
        )
        MediumSpace()
        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.acquisition_date_label), data = product.acquisitionDate.format()
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.discontinuation_date_label), data = product.discontinuationDate?.format()
            )
            }
        )
        MediumSpace()
        SeparatedRow(
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.minimun_stock_label), data = product.minimunStock?.toString()
            )
            },
            {
                ProductDetailsStamp(
                dataType = stringResource(R.string.labels_label), data = if (product.tags.isEmpty()) null else product.tags.toString()
            )
            }
        )
        MediumSpace()
        ProductDetailsStamp(
            dataType = stringResource(R.string.description_label)
        ){
            LargeTextBox(text = product.description)
        }
        MediumSpace()
        ProductDetailsStamp(
            dataType = stringResource(R.string.notes_label)
        ){
            LargeTextBox(text = product.notes)
        }
    }
}

@Composable
private fun ProductDetailsStamp(dataType : String, data : String?){
    Column (
        modifier = Modifier.width(175.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DetailsHead(text = dataType)
        SmallSpace()
        data?.let {
            NormalTextBox(text = data)
        } ?: NormalTextBox(text = stringResource(R.string.field_without_value))
    }
}

@Composable
private fun ProductDetailsStamp(dataType : String, state : ProductState){
    val textToShow = when(state){
        ProductState.new -> stringResource(R.string.state_as_new)
        ProductState.modified -> stringResource(R.string.state_as_modified)
        ProductState.verified -> stringResource(R.string.state_as_verified)
    }

    ProductDetailsStamp(dataType = dataType, data = textToShow)
}

@Composable
private fun ProductDetailsStamp(dataType : String, content : @Composable () -> Unit){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DetailsHead(text = dataType)
        SmallSpace()
        content.invoke()
    }
}

@Composable
private fun SeparatedRow(
    productDetailsStamp1 : @Composable () -> Unit,
    productDetailsStamp2: @Composable () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        productDetailsStamp1()
        HighSpace()
        productDetailsStamp2()
    }
}