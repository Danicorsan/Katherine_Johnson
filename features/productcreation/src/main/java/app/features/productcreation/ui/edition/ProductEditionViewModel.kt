package app.features.productcreation.ui.edition

import app.features.productcreation.ui.base.ProductBaseCreationViewModel


class ProductEditionViewModel(onGoBackNav : () -> Unit = {}) : ProductBaseCreationViewModel(onGoBackNav) {
    override fun onAcceptChanges() {
        onGoBackNav()
    }
}