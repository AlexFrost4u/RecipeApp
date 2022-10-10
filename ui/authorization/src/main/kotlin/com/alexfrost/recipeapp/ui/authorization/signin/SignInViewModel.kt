package com.alexfrost.recipeapp.ui.authorization.signin

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class SignInViewModel : ViewModel(), ContainerHost<SignInState, SignInSideEffect> {
    override val container = container<SignInState, SignInSideEffect>(SignInState())

    fun updateEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun updatePassword(password: String) = intent {
        reduce { state.copy(password = password) }
    }

    fun updatePreviewEnabled() = intent {
        reduce { state.copy(isPreviewEnabled = state.isPreviewEnabled.not()) }
    }
}

internal data class SignInState(
    val email: String = "",
    val password: String = "",
    val isPreviewEnabled: Boolean = false
)

internal sealed class SignInSideEffect
