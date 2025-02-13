package app.features.accountsignin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.CampoFormulario

@Composable
fun LoginScreen(
    email: String,
    password: String,
    onClickCrearCuenta: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: LoginViewModel
) {
    val state = viewModel.state
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(email, password) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.setCredentialsFromSignUp(email, password)
        }
    }

    if (state.success) {
        onSuccess()
    } else {
        if (showErrorDialog) {
            BaseAlertDialog(
                title = "Error",
                text = "Hay un problema con las credenciales",
                confirmText = "Ok",
                onConfirm = { showErrorDialog = false },
                onDismiss = { showErrorDialog = false })
        }
        if (state.isLoading){
            LoadingUi()
        }else {
            LoginScreenHost(
                state = state,
                onEmailChange = { viewModel.onEmailChange(it) },
                onPasswordChange = { viewModel.onPasswordChange(it) },
                onLoginClick = {
                    viewModel.login(
                        onSuccess = {},
                        onError = { showErrorDialog = true }
                    )
                },
                onClickCrearCuenta = onClickCrearCuenta
            )
        }
    }
}

@Composable
fun LoginScreenHost(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onClickCrearCuenta: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginScreenContent(
            state = state,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLoginClick = onLoginClick,
            onClickCrearCuenta = onClickCrearCuenta
        )
    }
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onClickCrearCuenta: () -> Unit
) {
    Text("¡Bienvenido!", style = MaterialTheme.typography.headlineSmall)
    Spacer(modifier = Modifier.height(24.dp))

    CampoFormulario(
        value = state.email,
        onValueChange = onEmailChange,
        isError = state.isEmailError,
        texto = "Correo"
    )
    state.emailErrorFormat?.let {
        Text(it, color = MaterialTheme.colorScheme.error, style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center))
    }
    Spacer(modifier = Modifier.height(8.dp))

    CampoFormulario(
        value = state.password,
        onValueChange = onPasswordChange,
        isError = state.isPasswordError,
        texto = "Contraseña",
        isPassword = true
    )
    state.passwordErrorFormat?.let {
        Text(it, color = MaterialTheme.colorScheme.error, style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center))
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onLoginClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = !state.isLoading
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
        } else {
            Text("Iniciar sesión")
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(16.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("¿No tienes cuenta?")
        TextButton(onClick = onClickCrearCuenta) {
            Text("Crear cuenta")
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onclickCrearCuenta = { /* No hacer nada en este ejemplo */ },
        onSucces = {},
        viewModel = LoginViewModel()
    )
}
*/