package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.data.product.EstadoProducto
import app.domain.invoicing.data.product.Producto
import app.domain.invoicing.data.product.complements.notes.Nota
import app.domain.invoicing.data.product.complements.notes.Notas
import app.domain.invoicing.data.product.complements.tags.Etiqueta
import app.domain.invoicing.data.product.complements.tags.Etiquetas
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant
import java.util.Date

object RepositorioProducto {
    private var nextId = 6
    private var almacenProductos : MutableMap<Int, Producto> = inicializarDemo()

    private fun inicializarDemo() : MutableMap<Int , Producto>{
        return mutableMapOf(
            1 to Producto(
                id = 1,
                codigo = "PROD001",
                nombre = "Laptop Gamer",
                nombreCorto = "Laptop",
                descripcion = "Laptop de alta gama para videojuegos",
                numeroSerie = "SN123456789",
                codigoModelo = "MODL001",
                tipoProducto = "Electrónica",
                categoria =  Category(
                    id = 1,
                    name = "Electronics",
                    shortName = "Elec",
                    description = "Devices and gadgets for everyday use",
                    image = ByteArray(0), // Imagen vacía como ejemplo
                    typeCategory = "Technology",
                    createdAt = Date()
                ),
                seccion = "Tecnología",
                estado = EstadoProducto.new,
                cantidad = 10u,
                precio = 1500.99,
                fechaAdquisicion = Instant.parse("2022-05-20T10:15:30Z"),
                fechaBaja = null,
                notas = Notas(listOf(Nota("Garantía", "Revisar estado de la garantía"))),
                etiquetas = Etiquetas(listOf(Etiqueta("tecnología"), Etiqueta("gaming"))),
                stockMinimo = 5u
            ),
            2 to Producto(
                id = 2,
                codigo = "PROD002",
                nombre = "Auriculares Inalámbricos",
                nombreCorto = "Auriculares",
                descripcion = "Auriculares con cancelación de ruido",
                numeroSerie = "SN987654321",
                codigoModelo = "MODL002",
                tipoProducto = "Electrónica",
                categoria = Category(
                    id = 2,
                    name = "Books",
                    shortName = "Books",
                    description = "Printed and digital books of various genres",
                    image = ByteArray(0), // Imagen vacía como ejemplo
                    typeCategory = "Media",
                    createdAt = Date()
                ),
                seccion = "Accesorios",
                estado = EstadoProducto.verified,
                cantidad = 50u,
                precio = 200.00,
                fechaAdquisicion = Instant.parse("2023-03-15T12:30:00Z"),
                fechaBaja = null,
                notas = Notas(listOf(Nota("Promoción", "Producto en promoción por temporada"))),
                etiquetas = Etiquetas(listOf(Etiqueta("audio"), Etiqueta("inalámbrico"))),
            ),
            3 to Producto(
                id = 3,
                codigo = "PROD003",
                nombre = "Silla Ergonómica",
                nombreCorto = "Silla",
                descripcion = "Silla ergonómica ajustable para oficina",
                numeroSerie = "SN1122334455",
                codigoModelo = "MODL003",
                tipoProducto = "Muebles",
                categoria = Category(
                    id = 3,
                    name = "Clothing",
                    shortName = "Cloth",
                    description = "Apparel for men, women, and kids",
                    image = ByteArray(0), // Imagen vacía como ejemplo
                    typeCategory = "Fashion",
                    createdAt = Date()
                ),
                seccion = "Mobiliario",
                estado = EstadoProducto.modified,
                cantidad = 20u,
                precio = 120.50,
                fechaAdquisicion = Instant.parse("2021-10-10T08:00:00Z"),
                fechaBaja = null,
                notas = Notas(listOf(Nota("Estado", "Producto en buen estado, requiere limpieza"))),
                etiquetas = Etiquetas(listOf(Etiqueta("ergonómica"), Etiqueta("oficina"))),
                stockMinimo = 5u
            ),
            4 to Producto(
                id = 4,
                codigo = "PROD004",
                nombre = "Smartphone",
                nombreCorto = "Teléfono",
                descripcion = "Teléfono inteligente con pantalla OLED",
                numeroSerie = "SN5566778899",
                codigoModelo = "MODL004",
                tipoProducto = "Electrónica",
                categoria =  Category(
                    id = 4,
                    name = "Groceries",
                    shortName = "Groc",
                    description = "Daily essentials and food items",
                    image = ByteArray(0), // Imagen vacía como ejemplo
                    typeCategory = "Essentials",
                    createdAt = Date()
                ),
                seccion = "Tecnología",
                estado = EstadoProducto.verified,
                cantidad = 30u,
                precio = 899.99,
                fechaAdquisicion = Instant.parse("2023-01-05T09:00:00Z"),
                fechaBaja = null,
                notas = Notas(listOf(Nota("Lanzamiento", "Edición especial limitada"))),
                etiquetas = Etiquetas(listOf(Etiqueta("smartphone"), Etiqueta("OLED"))),
                stockMinimo = 10u
            ),
            5 to Producto(
                id = 5,
                codigo = "PROD005",
                nombre = "Monitor UltraWide",
                nombreCorto = "Monitor",
                descripcion = "Monitor curvo UltraWide 34 pulgadas",
                numeroSerie = "SN9988776655",
                codigoModelo = "MODL005",
                tipoProducto = "Electrónica",
                categoria = Category(
                    id = 5,
                    name = "Furniture",
                    shortName = "Furn",
                    description = "Home and office furniture",
                    image = ByteArray(0), // Imagen vacía como ejemplo
                    typeCategory = "Home",
                    createdAt = Date()
                ),
                seccion = "Tecnología",
                estado = EstadoProducto.new,
                cantidad = 15u,
                precio = 499.99,
                fechaAdquisicion = Instant.parse("2023-06-10T15:00:00Z"),
                fechaBaja = null,
                notas = Notas(listOf(Nota("Accesorios", "Incluye cables HDMI y DisplayPort"))),
                etiquetas = Etiquetas(listOf(Etiqueta("monitor"), Etiqueta("curvo"))),
            )
        )
    }

    /**
     * Permite obtener todos los productos del almacen virtual de Productos.
     *
     * @return Un flujo que devuelve un mapa cuya clave es el Id del producto y valor su producto correspondiente
     */
    fun obtenerProductos() : Flow<Map<Int, Producto>> {
        return flow {
            delay(2000)
            emit(almacenProductos)
        }
    }

    /**
     * Añadir un producto al almacen virtual. Se asignará al producto añadido un Id unico para el Producto.
     *
     * @param producto El producto a añadir.
     */
    suspend fun agregarProducto(producto : Producto){
        delay(2000)
        val idToBeAssign = nextId++
        almacenProductos.put(idToBeAssign, producto.copy(id = idToBeAssign))
    }

    /**
     * Quitar un Producto del almacen a traves de su Id.
     *
     * @param idProducto La id del producto que quieres eliminar.
     */
    suspend fun quitarProducto(idProducto : Int){
        delay(2000)
        almacenProductos.remove(idProducto)
    }
}