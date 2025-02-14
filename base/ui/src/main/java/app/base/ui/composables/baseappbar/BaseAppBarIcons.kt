package app.base.ui.composables.baseappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.base.ui.R




sealed class BaseAppBarIcons{

    companion object{
        /**
         * Crea el objeto [NavigationIcon] correspondiente al icono normalizado para abrir el menú lateral
         * (drawer).
         *
         * @param onClick Acción a realizar al presionar el icono.
         * @return El objeto [NavigationIcon] correspondiente.
         */
        @Composable
        fun drawerMenuIcon(onClick : () -> Unit) : NavigationIcon {
            return NavigationIcon(
                icon = Icons.Default.Menu,
                contentDescription = stringResource(R.string.drawer_menu_icon_content_description),
                onClick = onClick
            )
        }

        /**
         * Crea el objeto [NavigationIcon] correspondiente al icono normalizado para volver a la pantalla
         * anterior.
         *
         * @param onClick Acción al presionar al presionar el icono.
         * @return El objeto [NavigationIcon] correspondiente.
         */
        @Composable
        fun goBackPreviousScreenIcon(onClick: () -> Unit) : NavigationIcon {
            return NavigationIcon(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.go_back_previous_screen_icon),
                onClick = onClick
            )
        }

        /**
         * Crea el objeto [Action] correspondiente para generar el icono normalizado para editar un elemento,
         * el cual es mostrado directamente en el [BaseAppBar] y no en el menú desplegable de objetos [Action].
         *
         *
         * @param typeElement El parametro [Action.contentDescription] utiliza el patrón "Editar %s" donde
         * "%s" se sustituye por este parametro que indique el elemento a editar.
         * @param onClick Acción ha realizar al presionar el icono.
         * @return El objeto [Action] correspondiente.
         */
        @Composable
        fun editElementVisibleIcon(typeElement : String, onClick: () -> Unit) : Action{
            return Action(
                icon = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit_element_action_icon, typeElement),
                onClick = onClick
            )
        }

        /**
         * Crea el objeto [Action] correspondiente para generar el icono normalizado para eliminar un elemento,
         * el cual es mostrado directamente en el [BaseAppBar] y no en el menú desplegable de objetos [Action].
         *
         * @param typeElement El parametro [Action.contentDescription] utiliza el patrón "Eliminar %s" donde
         * "%s" se sustituye por este parametro que indique el elemento a eliminar.
         * @param onClick Acción ha realizar al presionar el icono.
         * @return El objeto [Action] correspondiente.
         */
        @Composable
        fun deleteElementVisibleIcon(typeElement : String, onClick: () -> Unit) : Action{
            return Action(
                icon = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_element_action_icon, typeElement),
                onClick = onClick
            )
        }

    }

}