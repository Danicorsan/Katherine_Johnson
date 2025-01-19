package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class InventoryRepository {

    private val _inventories = MutableStateFlow<List<Inventory>>(emptyList())
    val inventories: Flow<List<Inventory>> = _inventories.asStateFlow()

    init {
        // Cargar algunos datos de prueba al inicializar el repositorio
        loadTestData()
    }

    /**
     * Carga datos de prueba en el repositorio.
     */
    private fun loadTestData() {
        val inventory1 = Inventory(
            id = 1,
            name = "Inventario de Ropa",
            description = "Inventario con ropa de diferentes tipos",
            items = listOf(
                Item(id = 1, name = "Camiseta Roja", description = "Camiseta de algodón, talla M"),
                Item(id = 2, name = "Pantalones Azules", description = "Pantalones de mezclilla, talla 32"),
                Item(id = 3, name = "Zapatos Negros", description = "Zapatos de cuero, talla 42"),
                Item(id = 4, name = "Camisa Verde", description = "Camisa de algodón, talla S"),
                Item(id = 5, name = "Pantalones Blancos", description = "Pantalones de mezclilla, talla 34"),
                Item(id = 6, name = "Zapatos Blancos", description = "Zapatos de cuero, talla 40"),
                Item(id = 7, name = "Camisa Azul", description = "Camisa de algodón, talla L"),
                Item(id = 8, name = "Pantalones Negros", description = "Pantalones de mezclilla, talla 36"),
                Item(id = 9, name = "Zapatos Rojos", description = "Zapatos de cuero, talla 38"),
                Item(id = 10, name = "Camisa Negra", description = "Camisa de algodón, talla XL")
            )
        )

        val inventory2 = Inventory(
            id = 2,
            name = "Inventario de Electrónica",
            description = "Inventario con varios dispositivos electrónicos",
            items = listOf(
                Item(id = 1, name = "Laptop", description = "Laptop HP con 16GB de RAM"),
                Item(id = 2, name = "Smartphone", description = "Smartphone Samsung Galaxy S21"),
                Item(id = 3, name = "Auriculares", description = "Auriculares inalámbricos Sony"),
                Item(id = 4, name = "Teclado", description = "Teclado USB con teclas RGB"),
                Item(id = 5, name = "Monitor", description = "Monitor LCD 24 pulgadas"),
                Item(id = 6, name = "Mouse", description = "Mouse inalambríco Logitech"),
                Item(id = 7, name = "Impresora", description = "Impresora multifuncional HP"),
                Item(id = 8, name = "Cargador", description = "Cargador de batería de 12V"),
                Item(id = 9, name = "Cable USB", description = "Cable USB 3.0 para dispositivos"),
                Item(id = 10, name = "Cable HDMI", description = "Cable HDMI para monitores")
            )
        )

        val inventory3 = Inventory(
            id = 3,
            name = "Inventario de Muebles",
            description = "Inventario con muebles de oficina",
            items = listOf(
                Item(id = 1, name = "Escritorio", description = "Escritorio de madera con cajones"),
                Item(id = 2, name = "Silla Ejecutiva", description = "Silla ergonómica de oficina"),
                Item(id = 3, name = "Archivador", description = "Archivador metálico de 4 cajones"),
                Item(id = 4, name = "Mesas de Comedor", description = "Mesas de comedor de madera"),
                Item(id = 5, name = "Cajones de Organización", description = "Cajones de organización de madera"),
                Item(id = 6, name = "Cajones de Organización", description = "Cajones de organización de madera"),
                Item(id = 7, name = "Cajones de Organización", description = "Cajones de organización de madera"),
                Item(id = 8, name = "Cajones de Organización", description = "Cajones de organización de madera"),
                Item(id = 9, name = "Cajones de Organización", description = "Cajones de organización de madera"),
                Item(id = 10, name = "Cajones de Organización", description = "Cajones de organización de madera")
            )
        )

        // Añadimos los inventarios de prueba a la lista
        _inventories.value = listOf(inventory1, inventory2, inventory3)
    }

    /**
     * Añade un nuevo inventario.
     * @param inventory Inventario a añadir.
     */
    fun addInventory(inventory: Inventory) {
        _inventories.value += inventory
    }

    /**
     * Actualiza un inventario existente.
     * @param updatedInventory Inventario actualizado.
     */
    open suspend fun updateInventory(updatedInventory: Inventory) {
        _inventories.value = _inventories.value.map { inventory ->
            if (inventory.id == updatedInventory.id) updatedInventory else inventory
        }
    }

    /**
     * Elimina un inventario.
     * @param inventory Inventario a eliminar.
     */
    fun deleteInventory(inventory: Inventory) {
        _inventories.value = _inventories.value.filter { it.id != inventory.id }
    }

    /**
     * Busca un inventario por su ID.
     * @param id ID del inventario.
     * @return Inventario encontrado o null si no existe.
     */
    open fun findInventoryById(id: Int): Inventory? {
        return _inventories.value.find { it.id == id }
    }

    /**
     * Añadir un artículo a un inventario.
     * @param inventoryId ID del inventario.
     * @param item Artículo a añadir.
     */
    fun addItemToInventory(inventoryId: Int, item: Item) {
        _inventories.value = _inventories.value.map { inventory ->
            if (inventory.id == inventoryId) {
                inventory.copy(items = inventory.items + item)
            } else {
                inventory
            }
        }
    }

    /**
     * Eliminar un artículo de un inventario.
     * @param inventoryId ID del inventario.
     * @param itemId ID del artículo a eliminar.
     */
    fun removeItemFromInventory(inventoryId: Int, itemId: Int) {
        _inventories.value = _inventories.value.map { inventory ->
            if (inventory.id == inventoryId) {
                inventory.copy(items = inventory.items.filter { (it as Item).id != itemId })
            } else {
                inventory
            }
        }
    }
}
