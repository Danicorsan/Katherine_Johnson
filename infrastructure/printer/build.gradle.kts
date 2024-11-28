plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.hilt)
}

android {
    namespace = "app.infrastructure.printer"
}

dependencies {
    // Módulos
    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)
    implementation(projects.infrastructure.firebase)

    // Flujos
    implementation(libs.kotlin.coroutines.android)

    // Utilería
    implementation(libs.kotlinx.datetime)
}