package com.alexfrost.recipeapp.ui.authorization.signin

import androidx.lifecycle.ViewModel
import com.alexfrost.recipeapp.ui.authorization.EmailValidation
import com.alexfrost.recipeapp.ui.authorization.FieldValidation
import com.alexfrost.recipeapp.ui.authorization.validateEmail
import com.alexfrost.recipeapp.ui.authorization.validateField
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class SignInViewModel : ViewModel(), ContainerHost<SignInState, SignInSideEffect> {
    override val container = container<SignInState, SignInSideEffect>(SignInState())

    fun updateEmail(email: String) = intent {
        reduce { state.copy(email = email, emailValidation = validateEmail(email)) }
    }

    fun updatePassword(password: String) = intent {
        reduce { state.copy(password = password, passwordValidation = validateField(password)) }
    }

    fun updatePreviewEnabled() = intent {
        reduce { state.copy(isPreviewEnabled = state.isPreviewEnabled.not()) }
    }

    fun signIn() = intent {
        reduce {
            state.copy(
                isValidationEnabled = state.isValidationEnabled.not(),
                emailValidation = validateEmail(state.email),
                passwordValidation = validateField(state.password)
            )
        }
    }
}

internal data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPreviewEnabled: Boolean = false,
    val isValidationEnabled: Boolean = false,
    val emailValidation: EmailValidation = EmailValidation.None,
    val passwordValidation: FieldValidation = FieldValidation.None,
)

internal sealed class SignInSideEffect {
    object NavigateToHome : SignInSideEffect()
}
