plugins {
    alias(libs.plugins.app.android.feature)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.features.sectiondetail"
}

dependencies {
    implementation(projects.domain.inventory)
}
