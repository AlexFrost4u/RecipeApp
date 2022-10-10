package com.alexfrost.recipeapp.ui.authorization.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexfrost.recipeapp.core.ui.rippleClickable
import com.alexfrost.recipeapp.core.ui.theme.RecipeAppTheme
import com.alexfrost.recipeapp.ui.authorization.EmailValidation
import com.alexfrost.recipeapp.ui.authorization.FieldValidation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import com.alexfrost.core.ui.R as CoreRes

@Composable
fun SignInScreen(
    openHome: () -> Unit,
    openForgotPassword: () -> Unit,
    openRegister: () -> Unit
) {
    SignInContent(
        viewModel = getViewModel(),
        openHome = openHome,
        openForgotPassword = openForgotPassword,
        openRegister = openRegister
    )
}

@Composable
internal fun SignInContent(
    viewModel: SignInViewModel,
    openHome: () -> Unit,
    openForgotPassword: () -> Unit,
    openRegister: () -> Unit
) {
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.NavigateToHome -> openHome()
                    is SignInSideEffect.NavigateToForgotPassword -> openForgotPassword()
                    is SignInSideEffect.NavigateToRegister -> openRegister()
                }
            }
        }
    }

    SignInContent(
        state = viewModel.container.stateFlow.collectAsState().value,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onPreviewClick = viewModel::updatePreviewEnabled,
        onForgotPasswordClick = {},
        onSignInClick = viewModel::signIn,
        onRegisterClick = {}
    )
}

@Composable
internal fun SignInContent(
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPreviewClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .imePadding()
            .statusBarsPadding()
            .background(RecipeAppTheme.colors.white)
    ) {
        val focusManager = LocalFocusManager.current

        Image(
            painter = painterResource(id = CoreRes.drawable.ic_people),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = CoreRes.string.sign_in),
            style = RecipeAppTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        )

        EmailInput(email = state.email, onValueChange = onEmailChange)

        ValidationError(
            errorMessage = stringResource(state.emailValidation.descriptionId),
            validationEnabled = state.validationEnabled,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        PasswordInput(
            password = state.password,
            previewEnabled = state.isPreviewEnabled,
            onValueChange = onPasswordChange,
            onPreviewClick = onPreviewClick,
            onDone = focusManager::clearFocus
        )

        ValidationError(
            errorMessage = stringResource(
                state.fieldValidation.descriptionId,
                stringResource(CoreRes.string.password)
            ),
            validationEnabled = state.validationEnabled,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        ForgotPasswordButton(
            onClick = onForgotPasswordClick,
            modifier = Modifier.align(Alignment.End)
        )

        SignInButton(
            onClick = {
                focusManager.clearFocus()
                onSignInClick()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = CoreRes.string.sign_in_desc_new_to_recipe),
                style = RecipeAppTheme.typography.small,
            )

            RegisterButton(onClick = onRegisterClick)
        }
    }
}

@Composable
internal fun ValidationError(
    errorMessage: String,
    validationEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = validationEnabled && errorMessage.isNotEmpty(),
        enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(),
        exit = shrinkVertically() + fadeOut(),
        modifier = modifier
    ) {
        Text(
            text = errorMessage,
            color = RecipeAppTheme.colors.red,
            style = RecipeAppTheme.typography.small,
            modifier = modifier.animateContentSize(
                animationSpec = tween(
                    durationMillis = 250,
                    easing = FastOutSlowInEasing
                )
            )
        )
    }
}

@Composable
internal fun EmailInput(
    email: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = RecipeAppTheme.colors.secondary,
            backgroundColor = RecipeAppTheme.colors.white,
            cursorColor = RecipeAppTheme.colors.primary,
            focusedIndicatorColor = RecipeAppTheme.colors.primary,
            unfocusedIndicatorColor = RecipeAppTheme.colors.silver
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = CoreRes.drawable.ic_email_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(RecipeAppTheme.colors.silver),
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = CoreRes.string.email),
                style = RecipeAppTheme.typography.small,
                color = RecipeAppTheme.colors.black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    )
}

@Composable
internal fun PasswordInput(
    password: String,
    previewEnabled: Boolean,
    onValueChange: (String) -> Unit,
    onPreviewClick: () -> Unit,
    onDone: () -> Unit
) {
    TextField(
        value = password,
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = RecipeAppTheme.colors.secondary,
            backgroundColor = RecipeAppTheme.colors.white,
            cursorColor = RecipeAppTheme.colors.primary,
            focusedIndicatorColor = RecipeAppTheme.colors.primary,
            unfocusedIndicatorColor = RecipeAppTheme.colors.silver
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = CoreRes.drawable.ic_lock_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(RecipeAppTheme.colors.silver),
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(
                    id = if (previewEnabled) {
                        CoreRes.drawable.ic_preview_24
                    } else {
                        CoreRes.drawable.ic_preview_off_24
                    }
                ),
                contentDescription = null,
                colorFilter = ColorFilter.tint(RecipeAppTheme.colors.silver),
                modifier = Modifier.rippleClickable(
                    onClick = onPreviewClick,
                    bounded = false
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (previewEnabled) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardActions = KeyboardActions(onDone = { onDone() }),
        label = {
            Text(
                text = stringResource(id = CoreRes.string.password),
                style = RecipeAppTheme.typography.small,
                color = RecipeAppTheme.colors.black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    )
}

@Composable
internal fun ForgotPasswordButton(onClick: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RecipeAppTheme.colors.white, RoundedCornerShape(16.dp))
            .rippleClickable(
                onClick = onClick,
                bounded = true,
            )
    ) {
        Text(
            text = stringResource(id = CoreRes.string.forgot_password),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.blue,
            style = RecipeAppTheme.typography.small,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

@Composable
internal fun SignInButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RecipeAppTheme.colors.blue, RoundedCornerShape(16.dp))
            .rippleClickable(
                onClick = onClick,
                bounded = true,
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = CoreRes.string.sign_in),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.white,
            style = RecipeAppTheme.typography.large,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
internal fun RegisterButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(RecipeAppTheme.colors.white, RoundedCornerShape(16.dp))
            .rippleClickable(
                onClick = onClick,
                bounded = true,
            )
    ) {
        Text(
            text = stringResource(id = CoreRes.string.sign_in_btn_register),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.blue,
            style = RecipeAppTheme.typography.small,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
internal fun SignInPreview() {
    RecipeAppTheme {
        SignInContent(
            state = SignInState(email = "example@example.com", password = "123qwe"),
            onEmailChange = {},
            onPasswordChange = {},
            onPreviewClick = {},
            onForgotPasswordClick = {},
            onSignInClick = {},
            onRegisterClick = {}
        )
    }
}

@Preview
@Composable
internal fun SignInPreviewError() {
    RecipeAppTheme {
        SignInContent(
            state = SignInState(
                emailValidation = EmailValidation.Invalid,
                fieldValidation = FieldValidation.Required,
                validationEnabled = true
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onPreviewClick = {},
            onForgotPasswordClick = {},
            onSignInClick = {},
            onRegisterClick = {}
        )
    }
}
