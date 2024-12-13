package app.domain.invoicing.product.complements

import app.domain.invoicing.product.Product

abstract class ProductAlarm(
    private val _product: Product
) {
    class MinimunStock(val product: Product) : ProductAlarm(product) {
        override fun executeComprobation() {
            product.minimunStock?.let {
                if (product.stock < product.minimunStock!!){
                    this.activateAlarm()
                }
            }
        }
    }

    private val observadores : MutableList<(ProductAlarm, Product) -> Unit> = emptyList<(ProductAlarm, Product) -> Unit>().toMutableList()

    protected fun activateAlarm(){
        observadores.forEach {
            it.invoke(this, _product)
        }
    }

    fun addObservator(observator : (ProductAlarm, Product) -> Unit){
        observadores.add(observator)
    }

    fun removeObservator(observator: (ProductAlarm, Product) -> Unit){
        observadores.remove(observator)
    }

    abstract fun executeComprobation()
}