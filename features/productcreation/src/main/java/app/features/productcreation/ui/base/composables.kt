package app.features.productcreation.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import app.domain.invoicing.category.Category
import app.features.productcreation.R

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

private val editTextMaxWithdFraction = 2/3f

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
        modifier = modifier.fillMaxWidth(editTextMaxWithdFraction),
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
        modifier = modifier.fillMaxWidth(editTextMaxWithdFraction),
        label = @Composable { Text(tag) },
    )
}

@Composable
fun DropDownMenuForCategory(
    categories : Iterable<Category>,
    categorySelected: Category?,
    onNewCategorySelected: (Category) -> Unit
    ){
    val noItemSelectedMessage = stringResource(R.string.no_selected_option)

    DropDownMenuTemplate(
        modifier = Modifier.fillMaxWidth(editTextMaxWithdFraction),
        showSelectedValueInTextField = {
            categorySelected?.name ?: noItemSelectedMessage
                                       },
        elementsList = categories,
        onNewItemSelected = onNewCategorySelected,
        howShowItem = {
            OutlinedTextField(
                readOnly = true,
                value = it.name,
                onValueChange = {},
            )
        },
        label = stringResource(R.string.categoria_etiqueta)
    )
}

@Composable
fun DropDownMenuForSection(
    sections : Iterable<String>,
    sectionSelected: String?,
    onNewSectionSelected: (String) -> Unit
){
    val noItemSelectedMessage = stringResource(R.string.no_selected_option)

    DropDownMenuTemplate(
        modifier = Modifier.fillMaxWidth(editTextMaxWithdFraction),
        showSelectedValueInTextField = {
            sectionSelected ?: noItemSelectedMessage
        },
        elementsList = sections,
        onNewItemSelected = onNewSectionSelected,
        howShowItem = {
            OutlinedTextField(
                readOnly = true,
                value = it,
                onValueChange = {},
            )
        },
        label = stringResource(R.string.seccion_etiqueta)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownMenuTemplate(
    modifier: Modifier = Modifier,
    showSelectedValueInTextField : () -> String,
    elementsList : Iterable<T>,
    onNewItemSelected : (T) -> Unit,
    howShowItem : @Composable (T) -> Unit,
    label: String
){
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded,
        onExpandedChange = { expanded = it },
        ) {
        OutlinedTextField(
            readOnly = true,
            modifier = modifier,
            value = showSelectedValueInTextField(),
            onValueChange = {},
            label = @Composable {Text(label)},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            elementsList.forEach{
                DropdownMenuItem(
                    text = { howShowItem(it) },
                    onClick = {
                        expanded = false
                        onNewItemSelected(it)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    selectedDateText: String,
    label: String,
    onNewDateSelected : (Long?) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box(
        modifier = Modifier.fillMaxWidth(editTextMaxWithdFraction)
    ) {
        OutlinedTextField(
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
                        .fillMaxWidth(80/100f)
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
