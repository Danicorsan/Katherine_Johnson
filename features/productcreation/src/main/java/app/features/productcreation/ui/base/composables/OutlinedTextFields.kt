package app.features.productcreation.ui.base.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.features.productcreation.ui.base.Specification

@Composable
fun OneLineEditText(
    modifier: Modifier = Modifier,
    text: String,
    change : (String) -> Unit,
    tag : String,
    error : Boolean = false,
    opcionesTeclado : KeyboardOptions = KeyboardOptions.Default
    ){
    OutlinedTextField(
        value = text,
        onValueChange = change,
        modifier = modifier.fillMaxWidth(Specification.editTextMaxWithdFraction),
        isError = error,
        label = @Composable { Text(tag) },
        singleLine = true,
        keyboardOptions = opcionesTeclado,
    )
}

@Composable
fun MultipleLineEditText(
    modifier: Modifier = Modifier,
    text: String,
    change : (String) -> Unit,
    tag : String,
    error : Boolean = false){
    OutlinedTextField(
        text,
        change,
        isError = error,
        modifier = modifier.fillMaxWidth(Specification.editTextMaxWithdFraction),
        label = @Composable { Text(tag) },
    )
}

