plugins {
    id("recipeapp.code.quality")
    id("recipeapp.library.compose")
}

dependencies {
    api(libs.androidx.appcompat)
    api(libs.bundles.compose)
    api(libs.accompanist.insets)
}
