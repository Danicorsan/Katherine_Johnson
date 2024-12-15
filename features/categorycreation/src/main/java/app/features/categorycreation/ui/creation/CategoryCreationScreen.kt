package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumSpace
import app.domain.invoicing.category.TypeCategory
import app.features.categorycreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreationScreen(
    viewModel: CategoryCreationViewModel = CategoryCreationViewModel(),
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
        content = { innerPadding ->
            CategoryCreationContent(
                viewModel = viewModel,
                onClickNewCategory = onClickNewCategory,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreationContent(
    viewModel: CategoryCreationViewModel,
    onClickNewCategory: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.crear_categoria),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )

        // Nombre de la Categoría
        OutlinedTextField(
            value = viewModel.state.name,
            onValueChange = { viewModel.onNameChange(it) },
            label = { Text(stringResource(R.string.nombre_de_la_categoria)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            isError = viewModel.state.isNameError,
            supportingText = {
                if (viewModel.state.isNameError) {
                    Text(text = "Ya hay una categoría con ese nombre")
                }
            },
        )

        // Descripción de la Categoría
        OutlinedTextField(
            value = viewModel.state.description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            label = { Text(stringResource(R.string.descripcion_de_la_categoria)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4,
            isError = viewModel.state.isDescriptionError,
            supportingText = {
                if (viewModel.state.isDescriptionError) {
                    Text(text = "La descripción no puede estar vacía")
                }
            },
        )

        // Nombre Corto
        OutlinedTextField(
            value = viewModel.state.shortName,
            onValueChange = { viewModel.onShortNameChange(it) },
            label = { Text(stringResource(R.string.nombre_corto)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            isError = viewModel.state.isShortNameError,
            supportingText = {
                if (viewModel.state.isShortNameError) {
                    Text(text = "Debe tener al menos tres caracteres sin caracteres especiales")
                }
            }
        )

        // Imagen
        OutlinedTextField(
            value = viewModel.state.image ?: "",
            onValueChange = { viewModel.onImageChange(it) },
            enabled = false,
            label = { Text(stringResource(R.string.imagen)) },
            modifier = Modifier.fillMaxWidth()
        )

        MediumSpace()

        // Dropdown para TypeCategory
        EditableExposedDropdownMenuTypeCategory(viewModel)

        // Botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                onClickNewCategory()
                viewModel.onDiscardChanges()
            }) {
                Text(stringResource(R.string.descartar_cambios))
            }

            Button(onClick = {
                viewModel.createCategory()
                if (viewModel.state.isError) {
                    showDialog = true
                } else {
                    onClickNewCategory()
                }
            }) {
                Text(stringResource(R.string.crear_categoria))
            }
        }
    }

    // Diálogo de error
    if (showDialog) {
        BaseAlertDialog(
            title = "Error al crear la categoría",
            text = "Por favor, revisa los campos e intenta nuevamente.",
            confirmText = "Aceptar",
            onConfirm = { showDialog = false },
            onDismiss = { showDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableExposedDropdownMenuTypeCategory(viewModel: CategoryCreationViewModel) {
    val options = TypeCategory.values().map { it.name }
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
            label = { Text("Selecciona una Categoría") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
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
    CategoryCreationScreen(onClickNewCategory = {}, onClickBack = {})
}
