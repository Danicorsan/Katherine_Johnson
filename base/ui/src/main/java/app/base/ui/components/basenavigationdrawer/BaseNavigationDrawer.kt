package app.base.ui.components.basenavigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import app.base.ui.composables.SmallSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BaseNavigationDrawer(
    drawerState: DrawerState,
    navController: NavController,
    screenContent: @Composable () -> Unit
) {
    val closeDrawerCoroutine = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(app.base.ui.R.drawable.default_avatar),
                        contentDescription = stringResource(app.base.ui.R.string.content_description_avatar_image),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        stringResource(app.base.ui.R.string.drawer_account_name_default_text),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                HorizontalDivider()

                BaseNavigationDrawerItem.getMainScreensItems(navController).forEach {
                    MakeNavigationItem(
                        baseNavigationDraweritem = it,
                        drawerState = drawerState,
                        navController = navController,
                        closeDrawerCoroutine = closeDrawerCoroutine
                    )
                }

                HorizontalDivider()

                BaseNavigationDrawerItem.getOtherScreensItems(navController).forEach {
                    MakeNavigationItem(
                        baseNavigationDraweritem = it,
                        drawerState = drawerState,
                        navController = navController,
                        closeDrawerCoroutine = closeDrawerCoroutine
                    )
                }

            }
        },
        content = screenContent
    )
}

@Composable
private fun MakeNavigationItem(
    baseNavigationDraweritem: BaseNavigationDrawerItem,
    drawerState: DrawerState,
    navController: NavController,
    closeDrawerCoroutine: CoroutineScope
) {
    NavigationDrawerItem(
        label = {
            Row {
                Icon(
                    baseNavigationDraweritem.icon,
                    contentDescription = baseNavigationDraweritem.contentDescription
                )
                SmallSpace()
                Text(text = baseNavigationDraweritem.label)
            }
        },
        selected = navController.currentBackStackEntry?.destination?.route == baseNavigationDraweritem.getAsociatedNavigationRoute(),
        onClick = {
            closeDrawerCoroutine.launch {
                drawerState.close()
            }
            baseNavigationDraweritem.onClick()
        }
    )
}