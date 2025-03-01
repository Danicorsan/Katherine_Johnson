plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.compose)
}

android {
    namespace = "app.base.ui"
}

dependencies {
    implementation(projects.base.utils)
    implementation(projects.domain.ddd)
    implementation(libs.kotlinx.datetime)
    implementation(libs.animation.lottie)
}