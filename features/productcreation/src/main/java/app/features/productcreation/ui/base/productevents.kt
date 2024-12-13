package app.features.productcreation.ui.base

data class ProductEvents(
    val confirmationAboutProduct : () -> Unit,
    val leavePage : () -> Unit,
    val onNameChange : (String) -> Unit
)