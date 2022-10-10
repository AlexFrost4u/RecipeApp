package com.alexfrost.recipeapp.ui.authorization

import com.alexfrost.recipeapp.ui.authorization.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authorizationModule = module {
    viewModel { SignInViewModel() }
}
