package app.domain.invoicing.product

sealed class ProductException(message : String) : Exception(message) {
    data object ProductAlreadyExists : ProductException("El producto ya existe")
    data object UnknowError : ProductException("Algo ha ido mal")
    data object ProductNotFound : ProductException("El producto no ha sido encontrado")
}