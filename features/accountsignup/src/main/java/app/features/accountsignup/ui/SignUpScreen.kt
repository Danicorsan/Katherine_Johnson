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
import androidx.compose.ui.unit.dp
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.CampoFormulario

@Composable
fun SignUpScreen(
    viewModel: RegisterViewModel,
    onRegisterSuccess: (String, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var showErrorDialog by remember { mutableStateOf(false) }

    if (showErrorDialog) {
        BaseAlertDialog(
            title = "Error",
            text = "Hay un problema con las credenciales",
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
        Text("Crea tu cuenta", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))
        CampoFormulario(
            value = viewModel.state.userName,
            onValueChange = { viewModel.onNameChange(it) },
            isError = viewModel.state.nameUserErrorFormat != null,
            texto = "Nombre",
            errorMessage = viewModel.state.nameUserErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.userSurname,
            onValueChange = { viewModel.onSurnameChange(it) },
            isError = viewModel.state.userErrorFormat != null,
            texto = "Apellidos",
            errorMessage = viewModel.state.userErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            isError = viewModel.state.emailErrorFormat != null,
            texto = "Correo",
            errorMessage = viewModel.state.emailErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            isError = viewModel.state.passwordErrorFormat != null,
            texto = "Contraseña",
            errorMessage = viewModel.state.passwordErrorFormat.orEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Registrando..." else "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text("¿Ya tienes cuenta?")
        TextButton(onClick = onLoginAccount) {
            Text("Inicia sesión")
        }
    }
}




