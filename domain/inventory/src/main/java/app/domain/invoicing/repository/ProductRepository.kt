package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.network.BaseResult
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
     * @return Un objeto base result que en caso de haber adquirido todos los con
     *          exito de la infraestructura, estos se almacenan como un mapa, cuya
     *          clave es la clave primaria del producto y el valor es el producto
     *          que contiene la clave, a partir de un objeto success, dentro de un Flow.
     *          (Por ahora siempre devuelve success)
     *
     */
    fun getAllProducts() : BaseResult<Flow<Map<Int, Product>>> {
        val allProductFlow = flow {
            delay(2000)
            emit(productWarehouse)
        }
        return BaseResult.Success(allProductFlow)
    }

    /**
     * Obtener la lista de productos cuya categoria designada sea la pasada por por parametro.
     *
     * @param category La categoria que se busca de los productos
     * @return Devuelve un objeto BaseResult, el cual devolverá un objeto Error en caso de algún
     *         tipo de excepción o un objeto Success cuando no ha ocurrido problemas para obtener
     *         los productos buscados. El objeto
     */

    fun getProductsByCategory(category: Category) : BaseResult<Flow<List<Product>>>{
        val productsFiltered = productWarehouse.filter {
            it.value.category.id == category.id
        }.values.toList()
        val flowWithProducts = flow {
            delay(2000)
            emit(productsFiltered)
        }
        return BaseResult.Success(flowWithProducts)
    }

    /**
     * Añadir un producto al almacen virtual. Se asignará al producto añadido un Id unico para el Producto.
     *
     * @param product El producto a añadir.
     *
     * @return Un objeto base result que informará si el proceso se ha realizado
     *          con exito, en el caso de que se haya devuelto un objeto de tipo
     *          Success, o un objeto Error conteniendo un ProductException
     *          (Por ahora solo devuelve Success)
     */
    suspend fun addProduct(product : Product) : BaseResult<Unit>{
        delay(2000)
        val idToBeAssign = nextId++
        productWarehouse.put(idToBeAssign, product.copy(id = idToBeAssign))
        return BaseResult.Success(Unit)
    }

    /**
     * Quitar un Producto del almacen a traves de su Id.
     *
     * @param productId La id del producto que quieres eliminar.
     *
     * @return Un objeto BaseResult que indica el estado final de la operación,
     *          devolvera un objeto Success si la operación ha sido exitosa,
     *          en caso de error, devolvera un objeto error.
     */
    suspend fun removeProduct(productId : Int) : BaseResult<Unit>{
        delay(2000)
        productWarehouse.remove(productId)
        return BaseResult.Success(Unit)
    }
}