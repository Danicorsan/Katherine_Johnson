plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "app.domain.inventory"
}
room {
    schemaDirectory("$projectDir/schemas")
}


dependencies {
    implementation(projects.base.utils)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlin.coroutines.core)
    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)
}