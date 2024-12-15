package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.complements.notes.Note
import app.domain.invoicing.product.complements.notes.Notes
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant
import java.util.Date

object ProductRepository {
    private var nextId = 6
    private var productWarehouse : MutableMap<Int, Product> = initializeDemo()

    private fun initializeDemo() : MutableMap<Int , Product>{
        return mutableMapOf(
            1 to Product(
                id = 1,
                code = "PROD001",
                name = "Laptop Gamer",
                shortName = "Laptop",
                description = "Laptop de alta gama para videojuegos",
                serialNumber = "SN123456789",
                modelCode = "MODL001",
                productType = "Electrónica",
                category =  Category(
                    id = 1,
                    name = "Electronics",
                    shortName = "Elec",
                    description = "Devices and gadgets for everyday use",
                    image = ByteArray(0).toString(), // Imagen vacía como ejemplo
                    createdAt = Date(),
                    fungible = true
                ),
                section = "Tecnología",
                state = ProductState.new,
                stock = 10u,
                price = 1500.99,
                acquisitionDate = Instant.parse("2022-05-20T10:15:30Z"),
                discontinuationDate = null,
                notes = Notes(listOf(Note("Garantía", "Revisar estado de la garantía"))),
                tags = Tags(listOf(Tag("tecnología"), Tag("gaming"))),
                minimunStock = 5u
            ),
            2 to Product(
                id = 2,
                code = "PROD002",
                name = "Auriculares Inalámbricos",
                shortName = "Auriculares",
                description = "Auriculares con cancelación de ruido",
                serialNumber = "SN987654321",
                modelCode = "MODL002",
                productType = "Electrónica",
                category = Category(
                    id = 2,
                    name = "Books",
                    shortName = "Books",
                    description = "Printed and digital books of various genres",
                    image = ByteArray(0).toString(), // Imagen vacía como ejemplo
                    createdAt = Date(),
                    fungible = false
                ),
                section = "Accesorios",
                state = ProductState.verified,
                stock = 50u,
                price = 200.00,
                acquisitionDate = Instant.parse("2023-03-15T12:30:00Z"),
                discontinuationDate = null,
                notes = Notes(listOf(Note("Promoción", "Producto en promoción por temporada"))),
                tags = Tags(listOf(Tag("audio"), Tag("inalámbrico"))),
            ),
            3 to Product(
                id = 3,
                code = "PROD003",
                name = "Silla Ergonómica",
                shortName = "Silla",
                description = "Silla ergonómica ajustable para oficina",
                serialNumber = "SN1122334455",
                modelCode = "MODL003",
                productType = "Muebles",
                category = Category(
                    id = 3,
                    name = "Clothing",
                    shortName = "Cloth",
                    description = "Apparel for men, women, and kids",
                    image = ByteArray(0).toString(), // Imagen vacía como ejemplo
                    createdAt = Date(),
                    fungible = false
                ),
                section = "Mobiliario",
                state = ProductState.modified,
                stock = 20u,
                price = 120.50,
                acquisitionDate = Instant.parse("2021-10-10T08:00:00Z"),
                discontinuationDate = null,
                notes = Notes(listOf(Note("Estado", "Producto en buen estado, requiere limpieza"))),
                tags = Tags(listOf(Tag("ergonómica"), Tag("oficina"))),
                minimunStock = 5u
            ),
            4 to Product(
                id = 4,
                code = "PROD004",
                name = "Smartphone",
                shortName = "Teléfono",
                description = "Teléfono inteligente con pantalla OLED",
                serialNumber = "SN5566778899",
                modelCode = "MODL004",
                productType = "Electrónica",
                category =  Category(
                    id = 4,
                    name = "Groceries",
                    shortName = "Groc",
                    description = "Daily essentials and food items",
                    image = ByteArray(0).toString(), // Imagen vacía como ejemplo
                    createdAt = Date(),
                    fungible = false
                ),
                section = "Tecnología",
                state = ProductState.verified,
                stock = 30u,
                price = 899.99,
                acquisitionDate = Instant.parse("2023-01-05T09:00:00Z"),
                discontinuationDate = null,
                notes = Notes(listOf(Note("Lanzamiento", "Edición especial limitada"))),
                tags = Tags(listOf(Tag("smartphone"), Tag("OLED"))),
                minimunStock = 10u
            ),
            5 to Product(
                id = 5,
                code = "PROD005",
                name = "Monitor UltraWide",
                shortName = "Monitor",
                description = "Monitor curvo UltraWide 34 pulgadas",
                serialNumber = "SN9988776655",
                modelCode = "MODL005",
                productType = "Electrónica",
                category = Category(
                    id = 5,
                    name = "Furniture",
                    shortName = "Furn",
                    description = "Home and office furniture",
                    image = ByteArray(0).toString(), // Imagen vacía como ejemplo
                    createdAt = Date(),
                    fungible = false
                ),
                section = "Tecnología",
                state = ProductState.new,
                stock = 15u,
                price = 499.99,
                acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
                discontinuationDate = null,
                notes = Notes(listOf(Note("Accesorios", "Incluye cables HDMI y DisplayPort"))),
                tags = Tags(listOf(Tag("monitor"), Tag("curvo"))),
            )
        )
    }

    /**
     * Permite obtener todos los productos del almacen virtual de Productos.
     *
     * @return Un flujo que devuelve un mapa cuya clave es el Id del producto y valor su producto correspondiente
     */
    fun getAllProducts() : Flow<Map<Int, Product>> {
        return flow {
            delay(2000)
            emit(productWarehouse)
        }
    }

    /**
     * Añadir un producto al almacen virtual. Se asignará al producto añadido un Id unico para el Producto.
     *
     * @param product El producto a añadir.
     */
    suspend fun addProduct(product : Product){
        delay(2000)
        val idToBeAssign = nextId++
        productWarehouse.put(idToBeAssign, product.copy(id = idToBeAssign))
    }

    /**
     * Quitar un Producto del almacen a traves de su Id.
     *
     * @param productId La id del producto que quieres eliminar.
     */
    suspend fun removeProduct(productId : Int){
        delay(2000)
        productWarehouse.remove(productId)
    }
}