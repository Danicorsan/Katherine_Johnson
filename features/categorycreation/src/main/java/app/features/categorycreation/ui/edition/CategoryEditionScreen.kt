package app.features.categorycreation.ui.edition

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.features.categorycreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEditionScreen(categoryEditionViewModel: CategoryEditionViewModel, onClick: () -> Unit) {
    // Estados para los campos de texto
    var oldCategoryName by remember { mutableStateOf("") }
    var newCategoryName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // TopAppBar en la parte superior
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.editar_categoria))
            },
            navigationIcon = {
                IconButton(onClick = { /* Acción para volver */ }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.volver))
                }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Categoría",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Campo para el nombre antiguo de la categoría
        OutlinedTextField(
            value = oldCategoryName,
            onValueChange = { oldCategoryName = it },
            label = { Text(stringResource(R.string.nombre_antiguo_de_la_categoria)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para el nuevo nombre de la categoría
        OutlinedTextField(
            value = newCategoryName,
            onValueChange = { newCategoryName = it },
            label = { Text(stringResource(R.string.nuevo_nombre_de_la_categoria)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para la descripción de la categoría
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(R.string.descripcion_de_la_categoria)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Espacia los elementos a los extremos
        ) {
            Button(
                onClick = {
                    // Acción para descartar cambios
                    println("Cambios descartados")
                }
            ) {
                Text(stringResource(R.string.descartar_cambios))
            }

            Button(
                onClick = {
                    onClick()
                    println("Antiguo: $oldCategoryName, Nuevo: $newCategoryName, Descripción: $description")
                }
            ) {
                Text(stringResource(R.string.guardar_cambios))
            }
        }


    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun Preview() {
    CategoryEditionScreen(categoryEditionViewModel)
}
*/