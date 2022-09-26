plugins {
    `kotlin-dsl`
}

group = "com.alexfrost.recipeapp"

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

gradlePlugin {
    plugins.register("android-application-compose-plugin") {
        id = "recipeapp.application.compose"
        implementationClass = "AndroidApplicationComposePlugin"
    }

    plugins.register("android-application-xml-plugin") {
        id = "recipeapp.application.xml"
        implementationClass = "AndroidApplicationXmlPlugin"
    }

    plugins.register("android-application-plugin") {
        id = "recipeapp.application"
        implementationClass = "AndroidApplicationPlugin"
    }

    plugins.register("android-library-compose-plugin") {
        id = "recipeapp.library.compose"
        implementationClass = "AndroidLibraryComposePlugin"
    }

    plugins.register("android-library-xml-plugin") {
        id = "recipeapp.library.xml"
        implementationClass = "AndroidLibraryXmlPlugin"
    }

    plugins.register("android-library-plugin") {
        id = "recipeapp.library"
        implementationClass = "AndroidLibraryPlugin"
    }

    plugins.register("code-quality-plugin") {
        id = "recipeapp.code.quality"
        implementationClass = "CodeQualityPlugin"
    }

    plugins.register("gradle-versions-plugin") {
        id = "recipeapp.gradle.versions"
        implementationClass = "GradleVersionPlugin"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle.build)
    implementation(libs.kotlin.stdlib)

    implementation(libs.detekt.plugin)
    implementation(libs.spotless.plugin)
    implementation(libs.ktlint.jlleitschuh.plugin)
    implementation(libs.gradle.versions.plugin)
}
