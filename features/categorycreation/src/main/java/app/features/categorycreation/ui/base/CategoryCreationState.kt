package app.features.categorycreation.ui.base

import java.util.Date

data class CategoryCreationState(
    var name: String = "",
    var description: String = "",
    val shortName: String = "",
    val image: String? = null,
    val createdAt: Date = Date(),
    var isError: Boolean = false,
    val isNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isShortNameError: Boolean = false,

    )