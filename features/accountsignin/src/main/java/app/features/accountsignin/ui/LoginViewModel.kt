package app.features.accountsignin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(email: String) {
        state = state.copy(email = email, emailErrorFormat = null, isEmailError = false)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(password = password, passwordErrorFormat = null, isPasswordError = false)
    }

    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (state.email.isBlank() || state.password.isBlank()) {
            onError("Los campos no pueden estar vacíos")
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                // Recolectando el flujo de login
                val result = repository.login(state.email, state.password).single()

                if (result.isSuccess) {
                    state = state.copy(success = true, isLoading = false)
                    onSuccess()
                } else {
                    state = state.copy(
                        userError = "Credenciales incorrectas",
                        isLoading = false
                    )
                    onError("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                state = state.copy(
                    userError = e.message ?: "Error desconocido",
                    isLoading = false
                )
                onError(e.message ?: "Error desconocido")
            }
        }
    }

    //Establece los dos parametros que vienen en de SignUp
    fun setCredentialsFromSignUp(email:String,password: String) {
        state = state.copy(
            email = email,
            password= password
        )
    }
}
