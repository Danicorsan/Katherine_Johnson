package app.features.productcreation.ui.edition

import app.features.productcreation.ui.base.ProductBaseCreationViewModel


class ProductEditionViewModel : ProductBaseCreationViewModel() {
    override fun onAcceptChanges() {
        super.onAcceptChanges()
        onGoBackNav()
    }
}