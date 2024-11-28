plugins {
    alias(libs.plugins.app.jvm.library)
}

dependencies {
    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)

    implementation(libs.kotlin.coroutines.core)
}