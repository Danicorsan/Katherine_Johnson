package app.features.categorycreation.ui.edition

import NoDataScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.CampoFormulario
import app.base.ui.composables.MediumButton
import app.domain.invoicing.category.Category
import app.features.categorycreation.R

@Composable
fun CategoryEditionScreen(
    viewModel: CategoryEditionViewModel,
    id: Int,
    onClickBack: () -> Unit,
    onClickNewCategory: () -> Unit
) {
    // Cargar la categoría al montar el Composable
    LaunchedEffect(id) {
        viewModel.loadCategory(id)
    }

    // Reiniciar los estados de los campos cuando el ID cambie
    val nameState = remember { mutableStateOf("") }
    val shortNameState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    val typeCategoryState = remember { mutableStateOf("") }

    // Actualizar los valores cuando se carga la categoría
    LaunchedEffect(viewModel.state.category) {
        viewModel.state.category?.let { category ->
            nameState.value = category.name
            shortNameState.value = category.shortName
            descriptionState.value = category.description
            typeCategoryState.value = category.typeCategory.name
        }
    }

    if (viewModel.state.category != null) {
        CategoryEditionContent(
            viewModel,
            viewModel.state.category!!,
            nameState,
            shortNameState,
            descriptionState,
            typeCategoryState,
            onClickBack = { onClickBack() },
            onConfirmClick = { viewModel.confirmChanges() },
            onClickNewCategory = { onClickNewCategory() }
        )
    } else {
        NoDataScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEditionContent(
    viewModel: CategoryEditionViewModel,
    category: Category,
    nameState: MutableState<String>,
    shortNameState: MutableState<String>,
    descriptionState: MutableState<String>,
    typeCategoryState: MutableState<String>,
    onClickBack: () -> Unit,
    onConfirmClick: () -> Unit,
    onClickNewCategory: () -> Unit
) {
    // Estados de error
    val nameError = remember { mutableStateOf(false) }
    val shortNameError = remember { mutableStateOf(false) }
    val descriptionError = remember { mutableStateOf(false) }
    val typeCategoryError = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.editar_categoria)) },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            MediumButton(
                onClick = {
                    onConfirmClick()
                    onClickNewCategory()
                },
                imageVector = Icons.Filled.Check,
                contentDescription = "Guardar cambios"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre
            CampoFormulario(
                isError = nameError.value,
                value = nameState.value,
                onValueChange = {
                    nameState.value = it
                    nameError.value = it.isEmpty()  // Validación simple
                    viewModel.onNameChange(it) // Actualización en el ViewModel
                },
                errorMessage = if (nameError.value) "El nombre no puede estar vacío" else "",
                isPassword = false,
                texto = "Nombre",
            )

            // Nombre corto
            CampoFormulario(
                isError = shortNameError.value,
                value = shortNameState.value,
                onValueChange = {
                    shortNameState.value = it
                    shortNameError.value = it.isEmpty()  // Validación simple
                    viewModel.onShortNameChange(it) // Actualización en el ViewModel
                },
                errorMessage = if (shortNameError.value) "El nombre corto no puede estar vacío" else "",
                isPassword = false,
                texto = "Nombre Corto",
            )

            // Descripción
            CampoFormulario(
                isError = descriptionError.value,
                value = descriptionState.value,
                onValueChange = {
                    descriptionState.value = it
                    descriptionError.value = it.isEmpty()  // Validación simple
                    viewModel.onDescriptionChange(it) // Actualización en el ViewModel
                },
                errorMessage = if (descriptionError.value) "La descripción no puede estar vacía" else "",
                isPassword = false,
                texto = "Descripción",
            )

            // Tipo de Categoría
            CampoFormulario(
                isError = typeCategoryError.value,
                value = typeCategoryState.value,
                onValueChange = {
                    typeCategoryState.value = it
                    typeCategoryError.value = it.isEmpty()
                },
                errorMessage = if (typeCategoryError.value) "Selecciona un tipo de categoría" else "",
                isPassword = false,
                texto = "Tipo de Categoría",
            )
        }
    }
}



/*
@Preview(showSystemUi = true)
@Composable
fun Preview() {
    val vm = CategoryEditionViewModel(CategoryRepository)
    CategoryEditionScreen(vm, 1, {}) { navController.popBackStack() }
}
*/
