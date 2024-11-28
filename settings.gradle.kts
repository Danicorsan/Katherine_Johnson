pluginManagement {
    includeBuild("build-domain")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Inventory"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":base:features")
include(":base:ui")
include(":base:utils")
include(":domain:ddd")
include(":domain:inventory")
include(":domain:navigation")
include(":features:accountsettings")
include(":features:accountsignin")
include(":features:accountsignup")
include(":features:productcreation")
include(":features:productdetail")
include(":features:productlist")
include(":features:dependencycreation")
include(":features:dependencylist")
include(":features:dependencydetail")
include(":features:sectiondetail")
include(":features:sectioncreation")
include(":features:sectionlist")
include(":features:categorydetail")
include(":features:categorycreation")
include(":features:categorylist")
include(":features:inventorycreation")
include(":features:inventorydetail")
include(":features:inventorylist")
include(":infrastructure:firebase")
include(":infrastructure:printer")
