package app.domain.invoicing.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz DAO para la gestión de cuentas en la base de datos.
 */
@Dao
interface AccountDao {
    /**
     * Get account
     *
     * @return
     */
    @Query("SELECT * FROM Account")
    fun getAccount(): Flow<List<Account>> // Devuelve una lista de cuentas

    /**
     * Get account from id
     *
     * @param accountId
     * @return
     */
    @Query("SELECT * FROM Account WHERE id = :accountId")
    fun getAccountFromId(accountId: Int): Flow<Account> // Obtiene una cuenta por ID

    /**
     * Insert account
     *
     * @param account
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account) // Inserta una cuenta en la BD
}
