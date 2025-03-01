package app.domain.invoicing.inventory

import InventoryIcon
import InventoryType
import java.time.LocalDate

/**
 * Representa un inventario.
 *
 * @property id Identificador único del inventario.
 * @property name Nombre del inventario.
 * @property description Descripción del inventario.
 * @property itemsCount Número de artículos en el inventario, puede ser nulo.
 * @property inventoryType Tipo de inventario, definido por la enumeración InventoryType.
 * @property createdAt Fecha de creación del inventario.
 * @property updatedAt Fecha de la última actualización del inventario.
 * @property icon Icono representativo del inventario, definido por la enumeración InventoryIcon.
 */
data class Inventory(
    val id: Int,
    val name: String,
    val description: String,
    val itemsCount: Int?,
    val inventoryType: InventoryType,
    val createdAt: LocalDate?,
    var updatedAt: LocalDate?,
    var icon: InventoryIcon
)