package app.domain.invoicing.repository

import app.domain.invoicing.account.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * Repositorio para gestionar cuentas de usuario.
 */
class AccountRepository(
    private val accountDao: AccountDao
) {

    /**
     * Valida las credenciales de inicio de sesión.
     */
    fun login(email: String, password: String): Flow<Result<Account>> = flow {
        delay(1000) // Simula un retraso antes de la autenticación
        val accounts = accountDao.getAccount().first() // Obtener la lista de cuentas
        val account = accounts.firstOrNull { it.email.value == email && it.password.value == password }

        if (account != null) {
            emit(Result.success(account))
        } else {
            emit(Result.failure(AccountException.NoExistAccount))
        }
    }

    /**
     * Registra una nueva cuenta.
     */
    suspend fun register(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Result<Unit> {
        val accounts = accountDao.getAccount().first() // Obtener lista de cuentas

        // Verifica si el email ya está registrado
        if (accounts.any { it.email.value == email }) {
            return Result.failure(AccountException.AccountExists)
        }

        // Crea la nueva cuenta
        val newAccount = Account(
            id = accounts.size + 1,
            email = Email(email),
            password = Password(password),
            name = name,
            surname = surname,
            username = "$name${surname.firstOrNull()}${accounts.size + 1}",
            birthdate = "01/01/2000" // Fecha ficticia por defecto
        )

        // Inserta la nueva cuenta en la base de datos
        accountDao.insertAccount(newAccount)
        return Result.success(Unit)
    }
}
