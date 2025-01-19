package app.domain.invoicing.repository

import app.domain.invoicing.product.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object ProductRepository {
    private var nextId = 8
    private var productWarehouse : MutableMap<Int, Product> = initializeProductsDemo()

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