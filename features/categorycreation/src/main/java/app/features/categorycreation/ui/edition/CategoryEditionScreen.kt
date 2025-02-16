package app.features.categorycreation.ui.edition

import NoDataScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.MediumButton
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.category.TypeCategory
import app.features.categorycreation.R
import app.features.categorycreation.ui.base.InputField

/**
 * Category edition event
 *
 * @constructor Create empty Category edition event
 */
sealed class CategoryEditionEvent {
    data class OnNameChange(val name: String) : CategoryEditionEvent()
    data class OnShortNameChange(val shortName: String) : CategoryEditionEvent()
    data class OnDescriptionChange(val description: String) : CategoryEditionEvent()
    data class OnTypeCategoryChange(val typeCategory: TypeCategory) : CategoryEditionEvent()
    data object ConfirmChanges : CategoryEditionEvent()
}

/**
 * Category edition screen
 *
 * @param viewModel
 * @param id
 * @param onClickBack
 * @receiver
 */
@Composable
fun CategoryEditionScreen(
    viewModel: CategoryEditionViewModel,
    id: Int,
    onClickBack: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.loadCategory(id)
    }

    val state by remember { derivedStateOf { viewModel.state } }

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.editar_categoria),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon { onClickBack() }
                )
            )
        },
        floatingActionButton = {
            MediumButton(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.guardar_cambios),
                onClick = {
                    if (state.itsAllOk) {
                        viewModel.onEvent(CategoryEditionEvent.ConfirmChanges)
                        onClickBack()
                    }
                })
        }
    ) { innerPadding ->
        if (state.isLoading) {
            LoadingUi()  // Mostrar la animación de carga
        }
        // Mostrar contenido solo si la categoría ha sido cargada
        if (!state.isLoading && state.category != null) {
            CategoryEditionContent(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(innerPadding)
            )
        } else if (!state.isLoading && state.notFoundError) {
            NoDataScreen()  // Si no hay categoría
        }

    }
}

/**
 * Category edition content
 *
 * @param state
 * @param onEvent
 * @param modifier
 * @receiver
 */
@Composable
fun CategoryEditionContent(
    state: CategoryEditionState,
    onEvent: (CategoryEditionEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            isError = state.isNameError,
            value = state.name,
            onValueChange = { onEvent(CategoryEditionEvent.OnNameChange(it)) },
            supportingText = { if (state.isNameError) stringResource(R.string.error_nombre_vacio) },
            label = "Nombre",
        )

        MediumSpace()

        InputField(
            value = state.shortName,
            onValueChange = { onEvent(CategoryEditionEvent.OnShortNameChange(it)) },
            label = "Nombre Corto",
            isError = state.isShortNameError,
            supportingText = {
                if (state.isShortNameError) stringResource(R.string.error_nombre_corto)
            },
            maxLines = 1
        )

        MediumSpace()

        InputField(
            value = state.description,
            onValueChange = { onEvent(CategoryEditionEvent.OnDescriptionChange(it)) },
            label = stringResource(R.string.descripcion_de_la_categoria),
            isError = state.isDescriptionError,
            supportingText = {
                if (state.isDescriptionError) {
                    Text(text = stringResource(R.string.error_descripcion_vacia))
                }
            },
            maxLines = 4
        )

        MediumSpace()

        Text(
            text = stringResource(R.string.elige_la_categoria),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        // Dropdown para seleccionar el tipo de categoría
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = state.typeCategory.name)
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                TypeCategory.entries.forEach { category ->
                    DropdownMenuItem(onClick = {
                        onEvent(CategoryEditionEvent.OnTypeCategoryChange(category))
                        expanded = false
                    }, text = { Text(text = category.name) })
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryEditionScreen() {
    CategoryEditionScreen(viewModel = hiltViewModel(), id = 1, onClickBack = {})
}
