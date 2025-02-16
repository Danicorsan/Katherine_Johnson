package app.features.accountsignin.ui

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.AccountRepository
import app.features.accountsignin.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val resources: Resources
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    /**
     * On email change
     *
     * @param email
     */
    fun onEmailChange(email: String) {
        state = state.copy(email = email, emailErrorFormat = null, isEmailError = false)
    }

    /**
     * On password change
     *
     * @param password
     */
    fun onPasswordChange(password: String) {
        state = state.copy(password = password, passwordErrorFormat = null, isPasswordError = false)
    }

    /**
     * Login
     *
     * @param onSuccess
     * @param onError
     * @receiver
     * @receiver
     */
    fun login(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (state.email.isBlank() || state.password.isBlank()) {
            onError(resources.getString(R.string.los_campos_no_pueden_estar_vacios))
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
                        userError = resources.getString(R.string.credenciales_incorrectas),
                        isLoading = false
                    )
                    onError(resources.getString(R.string.credenciales_incorrectas))
                }
            } catch (e: Exception) {
                state = state.copy(
                    userError = e.message ?: resources.getString(R.string.error_desconocido),
                    isLoading = false
                )
                onError(e.message ?: resources.getString(R.string.error_desconocido))
            }
        }
    }

    /**
     * Set credentials from sign up
     *
     * @param email
     * @param password
     */
    fun setCredentialsFromSignUp(email:String,password: String) {
        state = state.copy(
            email = email,
            password= password
        )
    }
}
