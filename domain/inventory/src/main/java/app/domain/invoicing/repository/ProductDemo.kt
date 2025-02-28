package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.complements.tags.Tag
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.repository.demodata.sectionDemoData
import kotlinx.datetime.Instant
import java.util.Date

internal fun initializeProductsDemo() : MutableMap<Int , Product>{
    val sections = sectionDemoData()
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
            category = Category(
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            ),
            section = sections[8],
            state = ProductState.new,
            stock = 10u,
            price = 1500.99,
            acquisitionDate = Instant.parse("2022-05-20T10:15:30Z"),
            discontinuationDate = null,
            notes = "Ordenadores algo viejos\nIgual no vale",
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
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            ),
            section = sections[3],
            state = ProductState.verified,
            stock = 50u,
            price = 200.00,
            acquisitionDate = Instant.parse("2023-03-15T12:30:00Z"),
            discontinuationDate = null,
            notes = "-Promocion\nProducto de temporada",
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
                name = "Services",
                shortName = "SERV",
                description = "Category for service offerings",
                image = null,
                createdAt = Date(),
                fungible = false
            ),
            section = sections[2],
            state = ProductState.modified,
            stock = 20u,
            price = 120.50,
            acquisitionDate = Instant.parse("2021-10-10T08:00:00Z"),
            discontinuationDate = null,
            notes = "Estado:\nProducto en buen estado, requiere limpieza",
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
            category = Category(
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            ),
            section = sections[5],
            state = ProductState.verified,
            stock = 30u,
            price = 899.99,
            acquisitionDate = Instant.parse("2023-01-05T09:00:00Z"),
            discontinuationDate = null,
            notes = "Lanzamiento: Edición especial limitada",
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
            category =             Category(
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            ),
            section = sections[7],
            state = ProductState.new,
            stock = 15u,
            price = 499.99,
            acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
            discontinuationDate = null,
            notes = "Accesorios\nIncluye cables HDMI y DisplayPort",
            tags = Tags(listOf(Tag("monitor"), Tag("curvo"))),
        ),
        6 to Product(
            id = 6,
            code = "PROD006",
            name = "Monitor UltraWide 2",
            shortName = "Monitor",
            description = "Monitor curvo UltraWide 34 pulgadas",
            serialNumber = "SN9988776656",
            modelCode = "MODL005",
            productType = "Electrónica",
            category = Category(
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            ),
            section = sections[0],
            state = ProductState.new,
            stock = 15u,
            price = 499.99,
            acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
            discontinuationDate = null,
            notes = "Accesorios\nIncluye cables HDMI y DisplayPort",
            tags = Tags(listOf(Tag("monitor"), Tag("curvo"))),
        ),
        7 to Product(
            id = 7,
            code = "PROD007",
            name = "Libro: Las mil maravillas",
            shortName = "Las mil maravillas",
            description = "Un libro de fantasia",
            serialNumber = "SN9988776657",
            modelCode = "MODL005",
            productType = "Libro de tapa dura",
            category = Category(
                id = 2,
                name = "Books",
                shortName = "BOOK",
                description = "Category for books and literature",
                image = null,
                createdAt = Date(),
                fungible = false
            ),
            section = sections[2],
            state = ProductState.new,
            stock = 15u,
            price = 499.99,
            acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
            discontinuationDate = null,
            notes = "Accesorios\nIncluye un marca páginas",
            tags = Tags(listOf(Tag("libro"), Tag("fantasia"))),
        )
    )
}