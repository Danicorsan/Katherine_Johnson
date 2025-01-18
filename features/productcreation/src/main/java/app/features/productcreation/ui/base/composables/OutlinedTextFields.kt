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
    label : String,
    error : Boolean = false,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
    obligatoryField : Boolean = false
    ){
    OutlinedTextField(
        value = text,
        onValueChange = change,
        modifier = modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        isError = error,
        label = @Composable { Text(label +
                if (obligatoryField) Specification.OBLIGATORYFIELDSMARK else "") },
        singleLine = true,
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun MultipleLineEditText(
    modifier: Modifier = Modifier,
    text: String,
    change : (String) -> Unit,
    label : String,
    error : Boolean = false,
    obligatoryField: Boolean = false
){
    OutlinedTextField(
        text,
        change,
        isError = error,
        modifier = modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        label = @Composable { Text(label +
                if (obligatoryField) Specification.OBLIGATORYFIELDSMARK else "") },
    )
}

