package app.features.productcreation.ui.base

import app.domain.invoicing.category.Category

data class DropDownItemsState (
    val categoriesList : Iterable<Category> = emptyList(),
    val selectedCategory : Category? = null,
    val sectionList : Iterable<String> = emptyList(),
    val selectedSection : String? = null,
)

data class DropDownItemsEvents (
    val onNewCategorySelected : (Category) -> Unit,
    val onNewSectionSelected : (String) -> Unit
){
    companion object{
        fun build(viewModel: ProductBaseCreationViewModel) : DropDownItemsEvents{
            return DropDownItemsEvents(
                onNewCategorySelected = viewModel::onNewCategorySelected,
                onNewSectionSelected = viewModel::onNewSectionSelected
            )
        }
    }
}