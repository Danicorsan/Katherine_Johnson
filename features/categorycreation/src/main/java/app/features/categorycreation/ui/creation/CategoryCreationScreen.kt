package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.MediumButton
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.categorycreation.R
import app.features.categorycreation.ui.base.CategoryImagePicker
import app.features.categorycreation.ui.base.EditableExposedDropdownMenuTypeCategory
import app.features.categorycreation.ui.base.ErrorDialog
import app.features.categorycreation.ui.base.FungibleSelectionField
import app.features.categorycreation.ui.base.InputField

/**
 * Category creation screen
 *
 * @param viewModel
 * @param onClickNewCategory
 * @param onClickBack
 * @receiver
 * @receiver
 */
@Composable
fun CategoryCreationScreen(
    viewModel: CategoryCreationViewModel,
    onClickNewCategory: () -> Unit,
    onClickBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.crear_categoria),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon(
                        onClick = {
                            onClickBack()
                            viewModel.onDiscardChanges()
                        }
                    )
                )
            )
        },
        floatingActionButton = {
            MediumButton(
                onClick = {
                    viewModel.createCategory()
                    if (!viewModel.state.isError)
                        onClickNewCategory()
                },
                Icons.Filled.Check,
                stringResource(app.base.ui.R.string.ok_button)
            )
        },
        content = { innerPadding ->
            if (viewModel.state.isLoading) {
                LoadingUi()
            } else {
                CategoryCreationContent(
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }

        },

        )
}

/**
 * Category creation content
 *
 * @param viewModel
 * @param modifier
 */
@Composable
fun CategoryCreationContent(
    viewModel: CategoryCreationViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryImagePicker(viewModel)
        MediumSpace()
        // Input Fields
        CategoryInputFields(viewModel)

        MediumSpace()

        // Dropdown for TypeCategory
        EditableExposedDropdownMenuTypeCategory(viewModel)

    }

    // Error Dialog
    if (viewModel.state.showDialog) {
        ErrorDialog { viewModel.dismissDialog() }
    }
}


/**
 * Category input fields
 *
 * @param viewModel
 */
@Composable
private fun CategoryInputFields(viewModel: CategoryCreationViewModel) {
    val state = viewModel.state

    InputField(
        value = state.name,
        onValueChange = viewModel::onNameChange,
        label = stringResource(R.string.nombre_de_la_categoria),
        isError = state.isNameError,
        supportingText = {
            if (state.isNameError) {
                Text(
                    text = if (state.name.isNotEmpty())
                        stringResource(R.string.error_nombre_existente)
                    else
                        stringResource(R.string.error_nombre_vacio)
                )
            }
        }
    )

    InputField(
        value = state.description,
        onValueChange = viewModel::onDescriptionChange,
        label = stringResource(R.string.descripcion_de_la_categoria),
        isError = state.isDescriptionError,
        supportingText = {
            if (state.isDescriptionError) {
                Text(text = stringResource(R.string.error_descripcion_vacia))
            }
        },
        maxLines = 4
    )

    InputField(
        value = state.shortName,
        onValueChange = viewModel::onShortNameChange,
        label = stringResource(R.string.nombre_corto),
        isError = state.isShortNameError,
        supportingText = {
            if (state.isShortNameError) {
                Text(
                    text = if (state.shortNameErrorMessage == "1") {
                        stringResource(R.string.error_nombre_corto)
                    } else {
                        stringResource(R.string.ya_hay_una_categoria_con_ese_nombre_corto)
                    }
                )
            }
        }
    )

    FungibleSelectionField(
        isFungible = state.fungible,
        onSelectionChange = viewModel::onFungibleChange

    )

}


@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryCreationScreen() {
    CategoryCreationScreen(hiltViewModel(), onClickNewCategory = {}, onClickBack = {})
}
