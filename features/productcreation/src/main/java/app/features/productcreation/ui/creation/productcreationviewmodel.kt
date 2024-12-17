package app.features.productcreation.ui.creation

import app.features.productcreation.ui.base.ProductBaseCreationViewModel

class ProductCreationViewModel : ProductBaseCreationViewModel() {
    override fun onAcceptChanges() {
        super.onAcceptChanges()
        onGoBackNav()
    }
}