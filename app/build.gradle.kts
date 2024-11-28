plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.android.application.compose)
    alias(libs.plugins.app.android.hilt)
    //alias(libs.plugins.gms.googleServices)
}

android {

    defaultConfig {
        applicationId = "com.example.inventory"
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "com.example.inventory.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    namespace = "com.example.inventory"
}

dependencies {
    // Módulos
    implementation(projects.base.ui)

    implementation(projects.domain.ddd)
    implementation(projects.domain.inventory)
    implementation(projects.domain.navigation)

    implementation(projects.infrastructure.printer)
    //implementation(projects.infrastructure.firebase)

    implementation(projects.features.accountsignup)
    implementation(projects.features.accountsignin)
    implementation(projects.features.accountsettings)
    implementation(projects.features.productcreation)
    implementation(projects.features.productlist)
    implementation(projects.features.productdetail)
    implementation(projects.features.dependencycreation)
    implementation(projects.features.dependencylist)
    implementation(projects.features.dependencydetail)
    implementation(projects.features.sectioncreation)
    implementation(projects.features.sectionlist)
    implementation(projects.features.sectiondetail)
    implementation(projects.features.categorycreation)
    implementation(projects.features.categorylist)
    implementation(projects.features.categorydetail)
    implementation(projects.features.inventorycreation)
    implementation(projects.features.inventorylist)
    implementation(projects.features.inventorydetail)



    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // UI
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigationcompose)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Testing Unitario
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)

    // Testing Instrumentado
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.androidx.navigation.test)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.androidx.test.core)
    kaptAndroidTest(libs.hilt.compiler)
}