package app.base.features.ui

fun String.isValidInteger(): Boolean {
    return this.contains(Regex("^([1-9][0-9]*|0)?$"))
}

fun String.isValidDecimal(): Boolean {
    val decimal = Regex("^(\\d+)?(\\.?)(\\d+)?$")
    return this.contains(decimal)
}