plugins {
    id("recipeapp.library.compose")
    id("recipeapp.code.quality")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.testing)

    implementation(libs.mvi.orbit)

    implementation(libs.koin.compose)
    implementation(libs.koin.android)

    implementation(libs.bundles.logging)

    implementation(libs.bundles.androidx.ui.compose)
}

tasks.withType<Test> { useJUnitPlatform() }
