package app.domain.invoicing.inventory

import java.time.LocalDate

data class Inventory(
    val id: Int,
    val name: String,
    val description: String,
    val itemsCount: Int?,
    val inventoryType: InventoryType,
    val createdAt: LocalDate,
    var updatedAt: LocalDate = LocalDate.now(),
    var icon: InventoryIcon
)

enum class InventoryType {
    WEEKLY,
    MONTHLY,
    TRIMESTRAL,
    SEMESTRAL,
    ANNUAL,
    PERMANENT
}

enum class InventoryIcon {
    ELECTRONICS,
    TECHNOLOGY,
    MATERIALS,
    SERVICES,
    WAREHOUSE,
    NONE
}