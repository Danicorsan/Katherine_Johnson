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
    PERMANENT
}