package app.features.accountsignup.ui

/**
 * Account register state
 *
 * @property userName
 * @property userSurname
 * @property email
 * @property password
 * @property nameUserErrorFormat
 * @property userErrorFormat
 * @property emailErrorFormat
 * @property passwordErrorFormat
 * @property isNameError
 * @property isSurnameError
 * @property isEmailError
 * @property isPasswordError
 * @property accountExistsError
 * @property serverError
 * @property success
 * @property isLoading
 * @constructor Create empty Account register state
 */
data class AccountRegisterState(
    val userName: String = "",
    val userSurname: String = "",
    val email: String = "",
    val password: String = "",
    // Campos de error para mostrar mensajes específicos
    val nameUserErrorFormat: String? = null,
    val userErrorFormat: String? = null,
    val emailErrorFormat: String? = null,
    val passwordErrorFormat: String? = null,
    // Indicadores booleanos para validación de errores
    val isNameError: Boolean = false,
    val isSurnameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    // Otros estados relacionados con el registro
    val accountExistsError: Boolean = false,
    val serverError: String? = null,
    val success: Boolean = false,
    val isLoading: Boolean = false
)
