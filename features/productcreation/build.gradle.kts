plugins {
    alias(libs.plugins.app.android.library.compose)
    alias(libs.plugins.app.android.feature)
}

android {
    namespace = "app.features.productcreation"
}

dependencies {
    implementation(projects.domain.inventory)
    implementation(libs.compose.coil)
}