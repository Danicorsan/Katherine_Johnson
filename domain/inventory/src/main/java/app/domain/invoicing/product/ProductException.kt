package app.domain.invoicing.product

sealed class ProductException(val message : String) {
    data object ProductAlreadyExists : ProductException("El producto ya existe")
    data object UnknowError : ProductException("Algo ha ido mal")
}