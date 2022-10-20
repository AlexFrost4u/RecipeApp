package com.alexfrost.recipeapp

import android.app.Application
import com.alexfrost.recipeapp.ui.authorization.authorizationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class RecipeAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RecipeAppApplication)
            modules(
                authorizationModule
            )
        }
    }
}
