package app.base.ui.composables


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CampoFormulario(
    value: String,
    isError: Boolean,
    texto: String,
    onValueChange: (String) -> Unit,
    errorMessage: String = "Formato Incorrecto",
    isPassword: Boolean = false
) {
    TextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        label = { Text(texto) },
        supportingText = {
            Row {
                Text(if (isError) "$errorMessage: " else "")
                Spacer(Modifier.weight(1f))
            }
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}