plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.features.categorydetail"
}

dependencies {
    implementation(projects.domain.inventory)

    implementation("io.coil-kt:coil-compose:2.5.0")
}
