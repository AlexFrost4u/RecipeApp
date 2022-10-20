package com.alexfrost.recipeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.alexfrost.recipeapp.ui.authorization.signin.SignInScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@ExperimentalAnimationApi
fun NavGraphBuilder.addAuthTopLevel() {
    navigation(
        route = Screen.Authorization.route,
        startDestination = AuthorizationScreen.SignIn.createRoute()
    ) {
        addSignIn()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addSignIn() {
    composable(
        route = AuthorizationScreen.SignIn.createRoute()
    ) {
        SignInScreen(
            openHome = {},
            openForgotPassword = {},
            openRegister ={}
        )
    }
}

internal sealed class AuthorizationScreen(private val route: String) {
    fun createRoute() = "authorization/$route"

    object SignIn : AuthorizationScreen("signIn")
}
