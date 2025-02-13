package app.base.ui.composables.baseappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import app.base.ui.R
import app.base.ui.composables.SmallSpace

/**
 * Permite crear un elemento App Bar generico para todos los modulos de la aplicación
 *
 * @param appBarState Contiene el estado de todos los datos necesarios para crear el elemento app bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(appBarState : BaseAppBarState){
    TopAppBar(
        title = { Text(appBarState.title)},
        navigationIcon = {
            IconButton(
                onClick = appBarState.navigationIcon.onClick
            ) {
                Icon(
                    imageVector = appBarState.navigationIcon.icon,
                    contentDescription = appBarState.navigationIcon.contentDescription
                )
            }
        },
        actions = {
            val actionsMenuElements = appBarState.actions.filter { !it.label.isNullOrEmpty() }
            val visibleActions = appBarState.actions.filter { it.label.isNullOrEmpty() }
            visibleActions.forEach {
                IconButton(
                    onClick = it.onClick
                ) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.contentDescription
                    )
                }
            }
            if (actionsMenuElements.isNotEmpty())
                DropDownMenuIcon(actionsMenuElements)
        }
    )
}

@Composable
private fun DropDownMenuIcon(actions: List<Action>){
    var isExpanded by remember { mutableStateOf(false) }
    Box{
        IconButton(
            onClick = {
                isExpanded = true
            }
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.actions_icon_base_app_bar_content_description)
            )
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            actions.forEach {
                DropdownMenuItem(
                    text = {
                        Row {
                            Icon(it.icon,
                                contentDescription = it.contentDescription
                            )
                            SmallSpace()
                            Text(it.label!!)//Las acciones pasadas deben tener un nombre
                        }
                    },
                    onClick = {
                        isExpanded = false
                    }
                )
            }
        }
    }
}
