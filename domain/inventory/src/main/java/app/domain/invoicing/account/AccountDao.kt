package app.domain.invoicing.account

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz DAO para la gestión de cuentas en la base de datos.
 */
@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    fun getAccount(): Flow<List<Account>> // Devuelve una lista de cuentas

    @Query("SELECT * FROM Account WHERE id = :accountId")
    fun getAccountFromId(accountId: Int): Flow<Account> // Obtiene una cuenta por ID

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account) // Inserta una cuenta en la BD
}
