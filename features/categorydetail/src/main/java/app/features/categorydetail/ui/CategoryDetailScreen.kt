package app.features.categorydetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.features.categorydetail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen() {
// TopAppBar en la parte superior
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.categoria))
        },
        navigationIcon = {
            IconButton(onClick = { /* Acción para volver */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.volver))
            }
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
        ) {

            Text(
                text = stringResource(R.string.detalle_de_la_categoria),
                fontSize = 24.sp, // Título más grande
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = stringResource(R.string.nombre),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Nombre de ejemplo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.descripcion),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Esta es una descripción de ejemplo de una categoría.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center, // Alinea el texto al centro si es multilineal
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = { /* Acción para editar categoría */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f) // Botón más ancho, ocupa 80% del ancho de la pantalla
            ) {
                Text(stringResource(R.string.editar_categoria))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    CategoryDetailScreen()
}
