package app.domain.invoicing.product.complements

import app.domain.invoicing.product.Producto

abstract class AlarmaProducto(
    private val _producto: Producto
) {
    class StockMinimo(val producto: Producto) : AlarmaProducto(producto) {
        override fun ejecutarComprobacion() {
            producto.stockMinimo?.let {
                if (producto.cantidad < producto.stockMinimo!!){
                    this.activarAlarma()
                }
            }
        }
    }

    private val observadores : MutableList<(AlarmaProducto, Producto) -> Unit> = emptyList<(AlarmaProducto, Producto) -> Unit>().toMutableList()

    protected fun activarAlarma(){
        observadores.forEach {
            it.invoke(this, _producto)
        }
    }

    fun añadirObservador(observador : (AlarmaProducto, Producto) -> Unit){
        observadores.add(observador)
    }

    fun quitarObservador(observador: (AlarmaProducto, Producto) -> Unit){
        observadores.remove(observador)
    }

    abstract fun ejecutarComprobacion()
}