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
fun OneLineEditText(
    text: String,
    change : (String) -> Unit,
    tag : String,
    error : Boolean = false,
    opcionesTeclado : KeyboardOptions = KeyboardOptions.Default
    ){
    OutlinedTextField(
        text,
        change,
        isError = error,
        label = @Composable { Text(tag) },
        singleLine = true,
        keyboardOptions = opcionesTeclado,
    )
}

@Composable
fun MultipleLineEditText(
    text: String,
    change : (String) -> Unit,
    tag : String,
    error : Boolean = false){
    OutlinedTextField(
        text,
        change,
        isError = error,
        label = @Composable { Text(tag) },
    )
}

@Composable
fun <T> DropDownMenuTemplate(
    expanded : Boolean = false,
    onDismiss : () -> Unit,
    elementsList : Iterable<T>,
    touchedItem : () -> Unit,
    howShowItem : @Composable (T) -> Unit
){
    DropdownMenu(
        expanded,
        onDismissRequest = onDismiss
        ) {
        elementsList.forEach {
            DropdownMenuItem(
                text = {howShowItem.invoke(it)},
                onClick = touchedItem
            )
        }
    }
}