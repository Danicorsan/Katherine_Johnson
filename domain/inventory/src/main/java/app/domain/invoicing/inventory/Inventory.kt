package app.domain.invoicing.inventory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.domain.invoicing.inventory.InventoryIcon.ELECTRONICS
import app.domain.invoicing.inventory.InventoryIcon.MATERIALS
import app.domain.invoicing.inventory.InventoryIcon.NONE
import app.domain.invoicing.inventory.InventoryIcon.SERVICES
import app.domain.invoicing.inventory.InventoryIcon.TECHNOLOGY
import app.domain.invoicing.inventory.InventoryIcon.WAREHOUSE
import app.domain.invoicing.inventory.InventoryState.ACTIVE
import app.domain.invoicing.inventory.InventoryState.HISTORY
import app.domain.invoicing.inventory.InventoryState.IN_PROGRESS
import app.domain.invoicing.inventory.InventoryType.ANNUAL
import app.domain.invoicing.inventory.InventoryType.MONTHLY
import app.domain.invoicing.inventory.InventoryType.PERMANENT
import app.domain.invoicing.inventory.InventoryType.SEMESTRAL
import app.domain.invoicing.inventory.InventoryType.TRIMESTRAL
import app.domain.invoicing.inventory.InventoryType.WEEKLY
import java.util.Date

/**
 * Representa un inventario.
 *
 * @property id Identificador único del inventario.
 * @property name Nombre del inventario.
 * @property description Descripción del inventario.
 * @property inventoryType Tipo de inventario, definido por la enumeración InventoryType.
 * @property historyDateAt Fecha en la que el inventario se colocó en histórico.
 * @property inProgressDateAt Fecha en la que el inventario se encuentra en progreso.
 * @property activeDateAt Fecha en la que el inventario se encuentra activo.
 * @property icon Icono representativo del inventario, definido por la enumeración InventoryIcon.
 */
@Entity(
    tableName = "inventories",
)
data class Inventory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "short_name")
    val shortName: String,
    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "inventory_type")
    val inventoryType: InventoryType,

    @ColumnInfo(name = "history_date_at")
    val historyDateAt: Date? = null,

    @ColumnInfo(name = "in_progress_date_at")
    val inProgressDateAt: Date,

    @ColumnInfo(name = "active_date_at")
    val activeDateAt: Date? = null,

    @ColumnInfo(name = "icon")
    var icon: InventoryIcon = NONE,

    @ColumnInfo(name = "state")
    var state: InventoryState = IN_PROGRESS
)

/**
 * Enumeración que representa los diferentes iconos para las categorías de inventario.
 *
 * Los valores de esta enumeración se utilizan para representar diferentes categorías de
 * inventario, como electrónica, tecnología, materiales, servicios y almacén.
 *
 * @property ELECTRONICS Icono para objetos electrónicos.
 * @property TECHNOLOGY Icono para objetos relacionados con la tecnología.
 * @property MATERIALS Icono para objetos materiales.
 * @property SERVICES Icono para objetos relacionados con servicios.
 * @property WAREHOUSE Icono para objetos almacenados en un almacén.
 * @property NONE Icono predeterminado cuando no se especifica una categoría.
 */
enum class InventoryIcon {
    /** Icono para objetos electrónicos. */
    ELECTRONICS,

    /** Icono para objetos relacionados con la tecnología. */
    TECHNOLOGY,

    /** Icono para objetos materiales. */
    MATERIALS,

    /** Icono para objetos relacionados con servicios. */
    SERVICES,

    /** Icono para objetos almacenados en un almacén. */
    WAREHOUSE,

    /** Icono predeterminado cuando no se especifica una categoría. */
    NONE
}
/**
 * Tipo de inventario, determina la periodicidad con la que se realizará un inventario.
 *
 * @property WEEKLY semanal, cada 7 días.
 *
 * El inventario se realizará cada 7 días, es decir, cada semana.
 *
 * @property MONTHLY mensual, cada 30 días.
 *
 * El inventario se realizará cada 30 días, es decir, cada mes.
 *
 * @property TRIMESTRAL trimestral, cada 90 días.
 *
 * El inventario se realizará cada 90 días, es decir, cada trimestre.
 *
 * @property SEMESTRAL semestral, cada 180 días.
 *
 * El inventario se realizará cada 180 días, es decir, cada semestre.
 *
 * @property ANNUAL anual, cada 365 días.
 *
 * El inventario se realizará cada 365 días, es decir, cada año.
 *
 * @property PERMANENT permanente, no tiene fecha de expiración.
 *
 * El inventario no tiene fecha de expiración, es decir, no se realizará nunca.
 */
enum class InventoryType {
    WEEKLY,
    MONTHLY,
    TRIMESTRAL,
    SEMESTRAL,
    ANNUAL,
    BIANNUAL,
    PERMANENT
}
/**
 * Estados en los que puede estar un inventario.
 *
 * @property ACTIVE Activo, el inventario está actualmente en uso.
 *
 * El inventario se encuentra actualmente en uso y se puede realizar un conteo en él.
 *
 * @property IN_PROGRESS En progreso, el inventario se encuentra en conteo.
 *
 * El inventario se encuentra en conteo y no se puede realizar un conteo en él.
 *
 * @property HISTORY Histórico, el inventario no se encuentra en uso y se guardan los resultados del conteo.
 *
 * El inventario no se encuentra en uso y se guardan los resultados del conteo en histórico.
 */
enum class InventoryState {
    ACTIVE,
    IN_PROGRESS,
    HISTORY
}