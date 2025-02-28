package app.features.productcreation.ui.base.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import app.features.productcreation.ui.base.Specification

/**
 * Permite crear un campo para escoger fechas a patir de
 * un [DatePicker]
 *
 * @param selectedDateText El texto a mostrar en el campo,
 * puede ser tanto la fecha seleccionada como un "no selecionado"
 * @param label Una etiqueta para el campo que indica a que corresponde
 * la fecha.
 * @param onNewDateSelected El evento lanzado cuando el usuario
 * ha escogido una fecha con [DatePicker]
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    selectedDateText: String,
    label: String,
    onNewDateSelected : (Long?) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box{
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
            value = selectedDateText,
            onValueChange = { },
            readOnly = true,
            label = @Composable { Text(label) },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = {
                    showDatePicker = false
                    onNewDateSelected(datePickerState.selectedDateMillis)
                },
                alignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)

                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}