plugins {
    id("recipeapp.application.compose")
    id("recipeapp.code.quality")
    id("recipeapp.gradle.versions")
    id("recipeapp.firebase")
}

android {
    // Temporary keystore
    signingConfigs {
        create("release") {
            keyAlias = "recipeapp"
            keyPassword = "recipeapp"
            storeFile = File("${rootDir.absolutePath}/keystore/recipeapp_keystore.jks")
            storePassword = "recipeapp"
        }
    }

    defaultConfig {
        applicationId = "com.alexfrost.recipeapp"
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")

            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(projects.core.ui)

    implementation(libs.bundles.androidx.ui.compose)
    implementation(libs.compose.navigation)

    implementation(libs.accompanist.navigation.animation)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.auth)
}
