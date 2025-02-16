package app.features.accountsignup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.CampoFormulario
import app.features.accountsignup.R

/**
 * Sign up screen
 *
 * @param viewModel
 * @param onRegisterSuccess
 * @param onNavigateToLogin
 * @receiver
 * @receiver
 */
@Composable
fun SignUpScreen(
    viewModel: RegisterViewModel,
    onRegisterSuccess: (String, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var showErrorDialog by remember { mutableStateOf(false) }

    //Bandera para mostrar dialogo
    if (showErrorDialog) {
        BaseAlertDialog(
            title = stringResource(R.string.error),
            text = stringResource(R.string.hay_un_problema_con_las_credenciales),
            confirmText = "Ok",
            onConfirm = { showErrorDialog = false },
            onDismiss = { showErrorDialog = false }
        )
    }

    SignUpHost(
        viewModel = viewModel,
        onRegister = {
            viewModel.register(
                onSuccess = { onRegisterSuccess(viewModel.state.email, viewModel.state.password) },
                onError = { showErrorDialog = true }
            )
        },
        onNavigateToLogin = onNavigateToLogin
    )
}

/**
 * Sign up host
 *
 * @param viewModel
 * @param onRegister
 * @param onNavigateToLogin
 * @receiver
 * @receiver
 */
@Composable
fun SignUpHost(
    viewModel: RegisterViewModel,
    onRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    SignUpContent(
        viewModel = viewModel,
        onRegister = onRegister,
        onLoginAccount = onNavigateToLogin,
        isLoading = viewModel.state.isLoading
    )
}

/**
 * Sign up content
 *
 * @param viewModel
 * @param onRegister
 * @param onLoginAccount
 * @param isLoading
 * @receiver
 * @receiver
 */
@Composable
fun SignUpContent(
    viewModel: RegisterViewModel,
    onRegister: () -> Unit,
    onLoginAccount: () -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(R.string.crea_tu_cuenta),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))
        CampoFormulario(
            value = viewModel.state.userName,
            onValueChange = { viewModel.onNameChange(it) },
            isError = viewModel.state.isNameError,
            texto = stringResource(R.string.nombre),
            errorMessage = viewModel.state.nameUserErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.userSurname,
            onValueChange = { viewModel.onSurnameChange(it) },
            isError = viewModel.state.userErrorFormat != null,
            texto = stringResource(R.string.apellidos),
            errorMessage = viewModel.state.userErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            isError = viewModel.state.emailErrorFormat != null,
            texto = stringResource(R.string.correo),
            errorMessage = viewModel.state.emailErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            isError = viewModel.state.passwordErrorFormat != null,
            texto = stringResource(R.string.contrasenia),
            errorMessage = viewModel.state.passwordErrorFormat.orEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(stringResource(R.string.registrarse))
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.ya_tienes_cuenta))
        TextButton(onClick = onLoginAccount) {
            Text(stringResource(R.string.inicia_sesion))
        }
    }
}




