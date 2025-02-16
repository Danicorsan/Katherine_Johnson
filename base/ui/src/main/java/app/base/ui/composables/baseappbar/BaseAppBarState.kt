package app.base.ui.composables.baseappbar

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Permite contener en un mismo objetos todos los datos que necesarios para crear el elemento
 * [BaseAppBar]
 *
 * @property title El título que mostrará el [BaseAppBar]
 * @property navigationIcon Un objeto [NavigationIcon] que contiene los datos para el icono de navegación correspondiente
 * @property actions Una lista de iconos de acción, si todos los objetos [Action] de está lista tienen
 * en sus parametros [Action.label] una cadena vacia o nula, la opción de despliege del menú de acciones
 * no se muestra, hace falta mínimo un [Action] con su parametro [Action.label] "rellenado".
 */
data class BaseAppBarState(
    val title : String,
    val navigationIcon : NavigationIcon,
    val actions : List<Action> = emptyList()
)

/**
 * Guarda los datos necesarios para crear el icono de navegación
 *
 * Hay métodos como [DrawerMenuIcon] y [GoBackPreviousScreenIcon] para crear
 * objetos [NavigationIcon] normalizados.
 *
 * @property icon El icóno a mostrar
 * @property onClick La acción a realizar al ser pulsado
 * @property contentDescription Descripción de lo que hace el icono
 */
data class NavigationIcon(
    val icon : ImageVector,
    val onClick: () -> Unit,
    val contentDescription: String
)

/**
 * Permite guardar los datos necesarios para crear un icono de acción para el BaseAppBar.
 *
 * Hay métodos como [EditElementVisibleIcon] o [DeleteElementVisibleIcon] para crear
 * objetos [Action] normalizados.
 *
 * @property icon El icono que representa la acción
 * @property contentDescription Descripción de lo que hace el icono
 * @property onClick La acción a ejecutar al pulsar sobre la acción
 * @property label Si es una cadena nula o vacia es un icono visible directamente en el AppBar, si no,
 * el icono es introducido en el menú de acciones desplegable.
 */
data class Action(
    val icon : ImageVector,
    val contentDescription : String,
    val onClick : () -> Unit,
    val label : String? = null,
    )