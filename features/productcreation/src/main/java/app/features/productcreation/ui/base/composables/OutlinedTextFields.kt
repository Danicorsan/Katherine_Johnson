package app.features.productcreation.ui.base.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.features.productcreation.ui.base.Specification

/**
 * Permite crear un campo de texto de solo una linea.
 *
 * @param modifier Modificador del campo de texto.
 * @param text El texto a mostrar.
 * @param onValueChange Evento lanzado al ser modificado.
 * @param label Etiqueta que indica a que corresponde el campo.
 * @param error Indica si la informacíon del campo es erronea.
 * @param keyboardOptions El teclado a mostrar al querer modificar el campo.
 * @param obligatoryField Indica si el campo es obligatorio añadiendo en la
 * etiqueta el String [Specification.OBLIGATORYFIELDSMARK].
 * @receiver
 */
@Composable
fun OneLineEditText(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange : (String) -> Unit,
    label : String,
    error : Boolean = false,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
    obligatoryField : Boolean = false
    ){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        isError = error,
        label = @Composable { Text(label +
                if (obligatoryField) Specification.OBLIGATORYFIELDSMARK else "") },
        singleLine = true,
        keyboardOptions = keyboardOptions,
    )
}

/**
 * Permite crear un campo de texto para lineas multiples.
 *
 * @param modifier Modificador del campo de texto.
 * @param text El texto a mostrar.
 * @param onValueChange Evento lanzado al ser modificado.
 * @param label Etiqueta que indica a que corresponde el campo.
 * @param error Indica si la informacíon del campo es erronea.
 * @param obligatoryField Indica si el campo es obligatorio añadiendo en la
 * etiqueta el String [Specification.OBLIGATORYFIELDSMARK].
 * @receiver
 */
@Composable
fun MultipleLineEditText(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange : (String) -> Unit,
    label : String,
    error : Boolean = false,
    obligatoryField: Boolean = false
){
    OutlinedTextField(
        text,
        onValueChange,
        isError = error,
        modifier = modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        label = @Composable { Text(label +
                if (obligatoryField) Specification.OBLIGATORYFIELDSMARK else "") },
    )
}

