buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle.build)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.spotless.plugin)
    }
}
