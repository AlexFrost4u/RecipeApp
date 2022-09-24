plugins {
    id("recipeapp.application.compose")
    id("recipeapp.code.quality")
    id("recipeapp.gradle.versions")
}

android {
    defaultConfig {
        applicationId = "com.alexfrost.recipeapp"
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx.ui)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.logging)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.ui.controller)

    implementation(libs.compose.destinations.core)

    testImplementation(libs.koin.test)
}
