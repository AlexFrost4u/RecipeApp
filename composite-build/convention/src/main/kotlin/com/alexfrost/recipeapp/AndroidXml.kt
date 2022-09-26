package com.alexfrost.recipeapp

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureAndroidXml(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            viewBinding = true
        }
    }
}
