package app.features.categorycreation.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.domain.invoicing.category.TypeCategory
import app.features.categorycreation.R
import app.features.categorycreation.ui.creation.CategoryCreationViewModel

/**
 * Editable exposed dropdown menu type category
 *
 * @param viewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableExposedDropdownMenuTypeCategory(viewModel: CategoryCreationViewModel) {
    val options = TypeCategory.entries.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options.first()) }

    fun String.toEnum(): TypeCategory {
        return TypeCategory.entries.firstOrNull { it.name == this } ?: TypeCategory.BASICOS
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text(stringResource(R.string.selecciona_una_categoria)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        viewModel.onTypeCategoryChange(selectionOption.toEnum())
                        expanded = false
                    },
                    text = { Text(selectionOption) }
                )
            }
        }
    }
}

