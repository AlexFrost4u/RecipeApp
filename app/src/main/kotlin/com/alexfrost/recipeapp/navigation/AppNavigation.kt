package com.alexfrost.recipeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Authorization.route,
        modifier = modifier
    ) {
        addAuthTopLevel()
    }
}

// Entry points of navigation graph
internal sealed class Screen(val route: String) {
    object Authorization : Screen("authorization")
}
