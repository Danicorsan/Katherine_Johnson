plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(projects.domain.ddd)
    implementation(projects.base.utils)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlin.coroutines.core)
}