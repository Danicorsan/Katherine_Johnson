package app.features.accountsignup.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.account.AccountException
import app.domain.invoicing.repository.AccountRepository
import kotlinx.coroutines.launch


//Inyectar resources

class RegisterViewModel(
    private val repository: AccountRepository,
) : ViewModel() {

    var state by mutableStateOf(AccountRegisterState())
        private set

    fun onNameChange(name: String) {
        state = state.copy(nameUserErrorFormat = null, isNameError = false)

        val nameRegex = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]{2,}$"
        if (!name.matches(Regex(nameRegex))) {
            state = state.copy(
                isNameError = true,
                userErrorFormat = "Formato de nombre incorrecto"
            )
        }
        state = state.copy(userName = name)
    }

    fun onSurnameChange(surname: String) {
        state = state.copy(userErrorFormat = null, isSurnameError = false)

        if (surname.isBlank()) {
            state = state.copy(
                isSurnameError = true,
                userErrorFormat = "Los apellidos no pueden estar vacíos"
            )
        }
        state = state.copy(userSurname = surname)
    }

    fun onEmailChange(email: String) {
        state = state.copy(emailErrorFormat = null, isEmailError = false)

        val emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
        if (!email.matches(Regex(emailRegex))) {
            state = state.copy(
                isEmailError = true,
                emailErrorFormat = "Formato de email incorrecto"
            )
        }
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(passwordErrorFormat = null, isPasswordError = false)

        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        if (!password.matches(Regex(passwordRegex))) {
            state = state.copy(
                isPasswordError = true,
                passwordErrorFormat = "Debe tener 8 caracteres, 1 mayúscula, 1 número y 1 especial."
            )
        }
        state = state.copy(password = password)
    }

    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            if (state.userName.isBlank() || state.userSurname.isBlank()) {
                onError("El nombre y los apellidos no pueden estar vacíos")
                state = state.copy(isLoading = false)
                return@launch
            }

            try {
                repository.register(
                    state.userName,
                    state.userSurname,
                    state.email,
                    state.password
                )
                state = state.copy(success = true, isLoading = false)
                onSuccess()
            } catch (e: AccountException) {
                val message = when (e) {
                    is AccountException.AccountExists -> "Ya existe una cuenta con el email proporcionado"
                    else -> e.message ?: "Error desconocido"
                }
                state = state.copy(serverError = message, isLoading = false)
                onError(message)
            }
        }
    }
}
