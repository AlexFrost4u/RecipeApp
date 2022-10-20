package com.alexfrost.recipeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.alexfrost.recipeapp.core.ui.theme.RecipeAppTheme
import com.alexfrost.recipeapp.navigation.AppNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecipeAppTheme {
                val navController = rememberAnimatedNavController()

                AppNavigation(navController = navController)
            }
        }
    }
}

