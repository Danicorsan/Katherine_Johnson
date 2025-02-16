package app.features.accountsignin.ui

/**
 * Login state
 *
 * @property email
 * @property password
 * @property passwordErrorFormat
 * @property emailErrorFormat
 * @property userError
 * @property success
 * @property isLoading
 * @property isOffline
 * @property isEmailError
 * @property isPasswordError
 * @constructor Create empty Login state
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordErrorFormat: String? = null,
    val emailErrorFormat: String? = null,
    val userError: String? = null,
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
)