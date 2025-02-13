package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.base.ui.composables.MediumSpace
import app.domain.invoicing.category.TypeCategory
import app.features.categorycreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreationScreen(
    viewModel: CategoryCreationViewModel,
    onClickNewCategory: () -> Unit,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.crear_categoria)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onClickBack()
                        viewModel.onDiscardChanges()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                }
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
                "Ok"
            )
        },
        content = { innerPadding ->
            CategoryCreationContent(
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding)
            )
        },

        )
}

@Composable
fun CategoryCreationContent(
    viewModel: CategoryCreationViewModel,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Input Fields
        CategoryInputFields(viewModel)

        MediumSpace()

        // Dropdown for TypeCategory
        EditableExposedDropdownMenuTypeCategory(viewModel)

    }

    // Error Dialog
    if (viewModel.state.showDialog) {
        ErrorDialog { viewModel.dimissDialog() }
    }
}


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

    //TODO Implementar imagen (pregunté a Lourdes y me dijo que de momento no lo implementase)
    OutlinedTextField(
        value = state.image ?: "",
        onValueChange = viewModel::onImageChange,
        enabled = false,
        label = { Text(stringResource(R.string.imagen)) },
        modifier = Modifier.fillMaxWidth()
    )


    FungibleSelectionField(
        isFungible = state.fungible,
        onSelectionChange = viewModel::onFungibleChange

    )

}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    supportingText: @Composable () -> Unit = {},
    maxLines: Int = 2
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = maxLines,
        isError = isError,
        supportingText = supportingText
    )
}

@Composable
fun FungibleSelectionField(
    isFungible: Boolean?,
    onSelectionChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MediumSpace()
        Text(
            text = stringResource(R.string.fungible),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isFungible == true,
                    onClick = { onSelectionChange(true) }
                )
                Text(
                    text = stringResource(R.string.si),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isFungible == false,
                    onClick = { onSelectionChange(false) }
                )
                Text(
                    text = stringResource(R.string.no),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ErrorDialog(onDismiss: () -> Unit) {
    BaseAlertDialog(
        title = stringResource(R.string.error_al_crear_la_categoria),
        text = stringResource(R.string.revisar_cambios_dialog),
        confirmText = stringResource(R.string.aceptar),
        onConfirm = onDismiss,
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableExposedDropdownMenuTypeCategory(viewModel: CategoryCreationViewModel) {
    val options = TypeCategory.entries.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options.first()) }

    fun String.toEnum(): TypeCategory {
        return TypeCategory.entries.firstOrNull { it.name == this } ?: TypeCategory.BASICOS
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text(stringResource(R.string.selecciona_una_categoria)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        viewModel.onTypeCategoryChange(selectionOption.toEnum())
                        expanded = false
                    },
                    text = { Text(selectionOption) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryCreationScreen() {
    CategoryCreationScreen(hiltViewModel(), onClickNewCategory = {}, onClickBack = {})
}
