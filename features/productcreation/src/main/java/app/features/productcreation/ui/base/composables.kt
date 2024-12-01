package app.features.productcreation.ui.base

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar( titleText : String, accionNavegacion : () -> Unit = {}){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = accionNavegacion) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun TextoEditableUnaLinea(
    texto: String,
    cambio : (String) -> Unit,
    etiqueta : String,
    error : Boolean = false,
    opcionesTeclado : KeyboardOptions = KeyboardOptions.Default
    ){
    OutlinedTextField(
        texto,
        cambio,
        isError = error,
        label = @Composable { Text(etiqueta) },
        singleLine = true,
        keyboardOptions = opcionesTeclado,
    )
}

@Composable
fun TextoEditableLineaMultiple(
    texto: String,
    cambio : (String) -> Unit,
    etiqueta : String,
    error : Boolean = false){
    OutlinedTextField(
        texto,
        cambio,
        isError = error,
        label = @Composable { Text(etiqueta) },
    )
}

@Composable
fun <T> MenuDesplegable(
    expandido : Boolean = false,
    tocarFuera : () -> Unit,
    listaElementos : Iterable<T>,
    alPulsarApartado : () -> Unit,
    mostrarContenido : @Composable (T) -> Unit
){
    DropdownMenu(
        expandido,
        onDismissRequest = tocarFuera
        ) {
        listaElementos.forEach {
            DropdownMenuItem(
                text = {mostrarContenido.invoke(it)},
                onClick = alPulsarApartado
            )
        }
    }
}