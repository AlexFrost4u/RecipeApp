plugins {
    id("recipeapp.library.compose")
    id("recipeapp.code.quality")
}

dependencies {
    implementation(projects.core.ui)

    implementation(libs.bundles.androidx.ui.compose)
}
