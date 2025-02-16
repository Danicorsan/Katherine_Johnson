package app.domain.invoicing.repository

import app.domain.invoicing.account.Account
import app.domain.invoicing.account.AccountException
import app.domain.invoicing.account.Email
import app.domain.invoicing.account.Password
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object AccountRepository {
    // Simulación de un dataset en memoria
    private val dataSet: MutableList<Account> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        dataSet.add(
            Account(
                id = 1,
                email = Email("daniel@gmail.com"),
                password = Password("HolaHola19?"),
                name = "Daniel",
                surname = "Cortés",
                username = "danics",
                birthdate = "02/03/2001"
            )
        )
        dataSet.add(
            Account(
                id = 1,
                email = Email("1"),
                password = Password("1"),
                name = "Prueba",
                surname = "SdAASD",
                username = "danasdasdics",
                birthdate = "02/03/2003"
            )
        )
        dataSet.add(
            Account(
                id = 1,
                email = Email("admin"),
                password = Password("admin"),
                name = "admin",
                surname = "admin",
                username = "admin",
                birthdate = "02/03/2003"
            )
        )
        dataSet.add(
            Account(
                id = 2,
                email = Email("lucia@gmail.com"),
                password = Password("Password123!"),
                name = "Lucía",
                surname = "García",
                username = "lucygar",
                birthdate = "15/07/1995"
            )
        )
    }

    /**
     * Valida las credenciales de inicio de sesión.
     */
    fun login(email: String, password: String): Flow<Result<Account>> = flow {
        delay(2000) // Agrega un retraso de 2 segundos antes de procesar la autenticación
        val account = dataSet.firstOrNull { it.email.value == email && it.password.value == password }
        if (account != null) {
            emit(Result.success(account))
        } else {
            emit(Result.failure(AccountException.NoExistAccount))
        }
    }

    /**
     * Devuelve una lista simulada de cuentas como flujo.
     */
    fun getAccounts(): Flow<List<Account>> = flow {
        delay(2000) // Simula una carga lenta
        emit(dataSet)
    }

    /**
     * Registra una nueva cuenta.
     */
    fun register(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Result<Unit> {
        // Verifica si el email ya está registrado
        if (dataSet.any { it.email.value == email }) {
            return Result.failure(AccountException.AccountExists)
        }

        // Agrega la nueva cuenta al dataset
        val newAccount = Account(
            id = dataSet.size + 1,
            email = Email(email),
            password = Password(password),
            name = name,
            surname = surname,
            username = "$name${surname.firstOrNull()}${dataSet.size + 1}",
            birthdate = "01/01/2000" // Fecha ficticia por defecto
        )
        dataSet.add(newAccount)
        return Result.success(Unit)
    }

    fun deleteAccount(account: Account) {
        dataSet.remove(account)
    }
}
