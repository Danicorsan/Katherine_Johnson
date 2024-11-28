plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.features.dependencylist"
}

dependencies {
    implementation(projects.domain.inventory)
}
