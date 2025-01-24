package app.base.ui.composables

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
 * Permite crear un elemento ExposedDropDownMenuBox (menú desplegable) para practicamente cualquier objeto,
 * reduciendo y simplificando el código requerido para casos de objetos complejos.
 *
 * La idea es que desde fuera de esta función, se guarde en una variable el objeto seleccionado y,
 * a partir de las funciones pasadas por parametro, poder maniobrar adecuadamente con las interacciones
 * del menú desplegable.
 *
 * Un ejemplo de su uso para mostrar las categorias disponibles en las pantallas de creación o edición
 * de un producto:
 *
 * ```
 * @Composable
 * fun ExposedDropDownMenuForCategory(
 *     categories : Iterable<Category>,
 *     categorySelected: Category?,
 *     onNewCategorySelected: (Category) -> Unit
 * ){
 *     val noItemSelectedMessage = "No seleccionado"
 *
 *     ExposedDropDownMenuTemplate(
 *         modifier = Modifier.fillMaxWidth(80/100f),
 *         showSelectedValueInTextField = {
 *             categorySelected?.name ?: noItemSelectedMessage
 *         },
 *         elementList = categories,
 *         onNewItemSelected = onNewCategorySelected,
 *         howShowEachItemInMenu = {
 *             Text(it.name)
 *         },
 *         label = "Categoria"
 *     )
 * }
 * ```
 *
 * @param T El tipo de objeto que se va ha manejar.
 *
 * @param modifier Permite modificar el cuadro de texto principal que mostraria el elemento seleccionado.
 *
 * @param showSelectedValueInTextField El cuadro de texto solo admite mostrar String
 * por lo que es necesario que apliques una lógica que interactue con el objeto seleccionado que devuelva
 * una representación del objeto en forma de String.
 *
 * @param elementList Los elementos que podrán ser seleccionados en el menú desplegado.
 *
 * @param onNewItemSelected Cuando una opción sea seleccionada por el usuario, el objeto seleccionado
 * será pasado por parametro a esta función. Ejemplo de uso, recoger el objeto pasado por parametro y guardarlo en tu
 * variable de objeto seleccionado.
 *
 * @param howShowEachItemInMenu Función composable de como debe mostrarse cada objeto en el menú
 * desplegado.
 *
 * @param label Etiqueta del cuadro de texto que indica al usuario que información almacena.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ExposedDropDownMenuTemplate(
    modifier: Modifier = Modifier,
    showSelectedValueInTextField : () -> String,
    elementList : Iterable<T>,
    onNewItemSelected : (T) -> Unit,
    howShowEachItemInMenu : @Composable (T) -> Unit,
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
            elementList.forEach{
                DropdownMenuItem(
                    text = { howShowEachItemInMenu(it) },
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