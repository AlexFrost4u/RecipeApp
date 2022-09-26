plugins {
    id("recipeapp.application.xml")
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
    implementation(libs.bundles.androidx.ui.xml)
}
