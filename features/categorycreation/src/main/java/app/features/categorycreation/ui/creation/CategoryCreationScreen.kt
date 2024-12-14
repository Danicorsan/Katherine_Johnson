package app.features.categorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.SmallSpace
import app.features.categorycreation.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreationScreen(
    viewModel: CategoryCreationViewModel = CategoryCreationViewModel(),
    onClickNewCategory: () -> Unit
) {

    if (viewModel.state.isError) {
        BaseAlertDialog(title = "Error", confirmText = "Confirmar", text = "Hola", onConfirm = {}, onDismiss = {})
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.crear_categoria))
                },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para volver */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                },
            )

            MediumSpace()

            Text(
                text = stringResource(R.string.crear_categoria),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
            MediumSpace()

            OutlinedTextField(
                value = viewModel.state.name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text(stringResource(R.string.nombre_de_la_categoria)) },
                modifier = Modifier.fillMaxWidth()
            )

            SmallSpace()

            OutlinedTextField(
                value = viewModel.state.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text(stringResource(R.string.descripcion_de_la_categoria)) },
                modifier = Modifier.fillMaxWidth()
            )

            SmallSpace()

            OutlinedTextField(
                value = viewModel.state.shortName,
                onValueChange = { viewModel.onShortNameChange(it) },
                label = { Text(stringResource(R.string.nombre_corto)) },
                modifier = Modifier.fillMaxWidth()
            )

            SmallSpace()

            OutlinedTextField(
                value = viewModel.state.image ?: "",
                onValueChange = { viewModel.onImageChange(it) },
                label = { Text(stringResource(R.string.imagen)) },
                modifier = Modifier.fillMaxWidth()
            )

            MediumSpace()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        println("Cambios descartados")
                    }
                ) {
                    Text(stringResource(R.string.descartar_cambios))
                }

                Button(
                    onClick = {
                        viewModel.createCategory(viewModel.state.name, viewModel.state.description)
                        if(viewModel.state.isError)
                            onClickNewCategory()
                    }
                ) {
                    Text(stringResource(R.string.crear_categoria))
                }
            }
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun Preview() {
    CategoryCreationScreen()
}
*/