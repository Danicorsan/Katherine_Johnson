plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.hilt)
}

android {
    namespace = "app.infrastructure.firebase"
}

dependencies {
    implementation(projects.domain.ddd)
    implementation(projects.base.utils)
    implementation(projects.domain.inventory)

   // implementation(platform(libs.firebase.bom))
   // implementation(libs.firebase.realtimedb)
   // implementation(libs.firebase.auth)

    implementation(libs.kotlinx.datetime)
}