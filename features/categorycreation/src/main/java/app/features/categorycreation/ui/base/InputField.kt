package app.features.categorycreation.ui.base

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

/**
 * Input field
 *
 * @param value
 * @param onValueChange
 * @param label
 * @param isError
 * @param supportingText
 * @param maxLines
 * @receiver
 * @receiver
 */
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    supportingText: @Composable () -> Unit = {},
    maxLines: Int = 2
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = maxLines,
        isError = isError,
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
}
