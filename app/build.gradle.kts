plugins {
    id("recipeapp.application.compose")
    id("recipeapp.gradle.versions")
    id("recipeapp.code.quality")
    id("recipeapp.firebase")
}

android {
    // Temporary keystore
    signingConfigs {
        create("release") {
            keyAlias = "recipeapp"
            keyPassword = "recipeapp"
            storePassword = "recipeapp"
            storeFile = File("${rootDir.absolutePath}/keystore/recipeapp_keystore.jks")
        }
    }

    defaultConfig {
        versionCode = 1
        versionName = "0.0.1"
        applicationId = "com.alexfrost.recipeapp"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")

            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
    }

    // Packaging rules
    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/LICENSE.md",
                "META-INF/licenses/ASM",
                "META-INF/LICENSE-notice.md",
                "win32-x86/attach_hotspot_windows.dll",
                "win32-x86-64/attach_hotspot_windows.dll",
            )
        )
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.testing)
    implementation(projects.ui.authorization)
    implementation(projects.feature.authorization.api)

    implementation(libs.compose.navigation)
    implementation(libs.bundles.androidx.ui.compose)

    implementation(libs.koin.android)

    implementation(libs.accompanist.navigation.animation)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.auth)
}
