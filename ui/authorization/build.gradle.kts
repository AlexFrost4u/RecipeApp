plugins {
    id("recipeapp.library.compose")
    id("recipeapp.code.quality")
}

dependencies {
    implementation(projects.core.ui)

    implementation(libs.mvi.orbit)

    implementation(libs.koin.compose)
    /*implementation(libs.koin.android)*/

    implementation(libs.bundles.androidx.ui.compose)
}
