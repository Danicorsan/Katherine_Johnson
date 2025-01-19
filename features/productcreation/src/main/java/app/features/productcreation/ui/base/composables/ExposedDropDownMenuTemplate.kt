package app.features.productcreation.ui.base.composables

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * <p>Permite crear un elemento ExposedDropDownMenuBox (menú desplegable) para practicamente cualquier objeto,
 * reduciendo y simplificando el código requerido.</p>
 *
 * <p>La idea es que desde fuera de esta función, se guarde en una variable el objeto seleccionado y,
 * a partir de las funciones pasadas por parametro, poder maniobrar adecuadamente con las interacciones
 * del menú desplegable</p>
 *
 * @param T El tipo de objeto con el que se va ha crear el ExposedDropDownMenuBox
 * @param modifier Permite modificar a gusto el cuadro de texto que indica el objeto selecionado
 * @param showSelectedValueInTextField El cuadro de texto solo admite mostrar String
 * por lo que es necesario que apliques una lógica que interactue con el objeto seleccionado que devuelva
 * una representación del objeto en forma de String.
 * @param elementsList La lista de opciones que se van a mostrar para escoger al desplegar el menú
 * @param onNewItemSelected Cuando una opción sea seleccionada, el objeto seleccionado será pasado por
 * parametro a esta función. Ejemplo de uso, recoger el objeto pasado por parametro y guardarlo en tu
 * variable de objeto seleccionado.
 * @param howShowItem Función composable de como debe mostrarse cada objeto pasado por el parametro elementsList
 * @param label Etiqueta del cuadro de texto para indicar al usuario que información almacena.
 * @receiver
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ExposedDropDownMenuTemplate(
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
            showSelectedValueInTextField(),
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            onValueChange = {},
            label = @Composable { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
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
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}