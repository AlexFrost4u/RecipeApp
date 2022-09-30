package com.alexfrost.recipeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

// Entry points of navigation graph
internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Authorization : Screen("authorization")
}

internal sealed class LeafScreen(private val route: String) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Home : LeafScreen("home")
    object Authorization : LeafScreen("authorization")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        addHomeTopLevel(navController = navController)
        addAuthTopLevel(navController = navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHomeTopLevel(navController: NavController) {
    navigation(
        route = Screen.Home.route,
        startDestination = LeafScreen.Home.createRoute(Screen.Home)
    ) {
        addHome(Screen.Home, navController = navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAuthTopLevel(navController: NavController) {
    navigation(
        route = Screen.Authorization.route,
        startDestination = LeafScreen.Authorization.createRoute(Screen.Authorization)
    ) {
        addAuthorization(Screen.Authorization, navController = navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHome(root: Screen, navController: NavController) {
    composable(
        route = LeafScreen.Home.createRoute(root)
    ) {
        HomeScreen(
            onNavigateToAuthScreen = {
                navController.navigate(Screen.Authorization.route)
            }
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAuthorization(root: Screen, navController: NavController) {
    composable(
        route = LeafScreen.Authorization.createRoute(root)
    ) {
        AuthorizationScreen(
            onBackPressed = {
                navController.navigateUp()
            }
        )
    }
}

@Composable
internal fun HomeScreen(onNavigateToAuthScreen: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Home Screen",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Text(
                text = "Navigate",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, Color.Black,RoundedCornerShape(40))
                    .padding(8.dp)
                    .clickable(onClick = onNavigateToAuthScreen)
            )
        }
    }
}

@Composable
internal fun AuthorizationScreen(onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Authorization Screen",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Text(
                text = "< -",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, Color.Black,RoundedCornerShape(40))
                    .padding(8.dp)
                    .clickable(onClick = onBackPressed)
            )
        }
    }
}
