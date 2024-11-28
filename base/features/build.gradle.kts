plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.hilt)
}

android {
    namespace = "app.base.features"
}

dependencies {
    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)
    implementation(projects.base.utils)

    // Android
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Kotlinx
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.runtime.android)

    // Unit Testing
    testImplementation(kotlin("test"))
    testImplementation(libs.junit)
}