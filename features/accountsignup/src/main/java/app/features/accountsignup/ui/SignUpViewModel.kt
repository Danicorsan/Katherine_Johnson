package app.features.accountsignup.ui

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.account.AccountException
import app.domain.invoicing.repository.AccountRepository
import app.features.accountsignup.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Register view model
 *
 * @property repository
 * @property resources
 * @constructor Create empty Register view model
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val resources: Resources
) : ViewModel() {

    var state by mutableStateOf(AccountRegisterState())
        private set

    /**
     * On name change
     *
     * @param name
     */
    fun onNameChange(name: String) {
        state = state.copy(nameUserErrorFormat = null, isNameError = false)

        val nameRegex = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]{2,}$"
        if (!name.matches(Regex(nameRegex))) {
            state = state.copy(
                isNameError = true,
                nameUserErrorFormat = resources.getString(R.string.formato_de_nombre_incorrecto)
            )
        }
        state = state.copy(userName = name)
    }

    /**
     * On surname change
     *
     * @param surname
     */
    fun onSurnameChange(surname: String) {
        state = state.copy(userErrorFormat = null, isSurnameError = false)

        val nameRegex = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]{2,}$"
        if (!surname.matches(Regex(nameRegex))) {
            state = state.copy(
                isSurnameError = true,
                userErrorFormat = resources.getString(R.string.formato_de_apellidos_incorrecto)
            )
        }
        state = state.copy(userSurname = surname)
    }

    /**
     * On email change
     *
     * @param email
     */
    fun onEmailChange(email: String) {
        state = state.copy(emailErrorFormat = null, isEmailError = false)

        val emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
        if (!email.matches(Regex(emailRegex))) {
            state = state.copy(
                isEmailError = true,
                emailErrorFormat = resources.getString(R.string.formato_de_email_incorrecto)
            )
        }
        state = state.copy(email = email)
    }

    /**
     * On password change
     *
     * @param password
     */
    fun onPasswordChange(password: String) {
        state = state.copy(passwordErrorFormat = null, isPasswordError = false)

        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        if (!password.matches(Regex(passwordRegex))) {
            state = state.copy(
                isPasswordError = true,
                passwordErrorFormat = resources.getString(R.string.error_contrasenia_caracteres)
            )
        }
        state = state.copy(password = password)
    }

    /**
     * Register
     *
     * @param onSuccess
     * @param onError
     * @receiver
     * @receiver
     */
    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            if (state.userName.isBlank() || state.userSurname.isBlank()) {
                onError(resources.getString(R.string.el_nombre_y_los_apellidos_no_pueden_estar_vacios))
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
                    is AccountException.AccountExists -> resources.getString(R.string.ya_existe_una_cuenta_con_el_email_proporcionado)
                    else -> e.message ?: resources.getString(R.string.error_desconocido)
                }
                state = state.copy(serverError = message, isLoading = false)
                onError(message)
            }
        }
    }
}