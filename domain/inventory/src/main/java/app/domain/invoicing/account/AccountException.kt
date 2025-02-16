package app.domain.invoicing.account

/**
 * Account exception, contiene todos los posibles errores que se puedan
 * encontrar en el caso de uso de Login y de SignUp
 *
 * @constructor
 *
 * @param message
 */
sealed class AccountException(message: String) : Exception(message) {
    data class TakenEmail(var email: String) : AccountException("Ya existe una cuenta con $email")
    data object NoExistAccount : AccountException("La cuenta no existe")
    data object InvalidPassword : AccountException("La contraseña no cumple con los requisitos")
    data object EmptyField : AccountException("Todos los campos son obligatorios")
    data object ServerError : AccountException("Error en el servidor. Por favor, intenta nuevamente")
    data object AccountExists : AccountException("La cuenta ya existe")
}
