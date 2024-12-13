plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.features.inventorycreation"
}

dependencies {
    implementation(projects.domain.inventory)
    implementation(project(":features:inventorylist"))
}
