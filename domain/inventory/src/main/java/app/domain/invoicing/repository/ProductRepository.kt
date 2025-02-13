package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductException
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
     * Permite obtener un objeto BaseResult para obtener un producto a traves de su id
     *
     * @return Un objeto base result que en caso de haber adquirido el producto con
     *          exito de la infraestructura, este se introduce en un objeto BaseResult.Success
     *          en caso contrario devuelve un BaseResult.Error.
     *
     */
    suspend fun getProductById(id : Int) : BaseResult<Product> {
        delay(2000)
        productWarehouse[id]?.let {
            return BaseResult.Success(it)
        } ?: run {
            return BaseResult.Error(ProductException.ProductNotFound)
        }
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
     * Actualiza un producto ya existente en la infraestructura.
     *
     * @param updatedProduct Se usa su id para buscar el producto a actualizar en la infraestrucutra y se actualiza
     * con los demas datos
     * @return Un objeto BaseResult.Success si todo ha ido bien (por ahora siempre
     * devuelve BaseResult.Success)
     */
    suspend fun updateExistingProduct(updatedProduct: Product) : BaseResult<Unit>{
        delay(2000)
        productWarehouse[updatedProduct.id!!] = updatedProduct
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