pluginManagement {
    includeBuild("composite-build")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "RecipeApp"

// App modules
include(":app")

// Core modules
include(
    ":core:ui",
    ":core:testing"
)

// UI modules
include(":ui:authorization")
