package com.alexfrost.recipeapp.ui.authorization.signin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexfrost.recipeapp.core.ui.RecipeAppTheme
import com.alexfrost.recipeapp.core.ui.rippleClickable
import com.alexfrost.core.ui.R as CoreRes

@Composable
fun SignInScreen() {
    SignInContent()
}

@Composable
internal fun SignInContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .imePadding()
            .statusBarsPadding()
            .background(RecipeAppTheme.colors.white),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var previewEnabled by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current

        Image(
            painter = painterResource(id = CoreRes.drawable.ic_people),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = CoreRes.string.login),
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )

        EmailInput(email = email, onValueChange = { email = it })

        PasswordInput(
            password = password,
            previewEnabled = previewEnabled,
            onValueChange = { password = it },
            onPreviewClick = { previewEnabled = previewEnabled.not() },
            onDone = focusManager::clearFocus
        )

        ForgotPasswordButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End))

        LoginButton(onClick = focusManager::clearFocus)

        Spacer(modifier = Modifier.weight(1f))


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "New to Recipe?",
                fontSize = 14.sp,
                modifier = Modifier.padding(5.dp)
            )

            RegisterButton(onClick = { /*TODO*/ })
        }
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
                color = RecipeAppTheme.colors.black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
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
                color = RecipeAppTheme.colors.black
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}

@Composable
internal fun ForgotPasswordButton(onClick: () -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .height(30.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RecipeAppTheme.colors.white, RoundedCornerShape(16.dp))
            .rippleClickable(
                onClick = onClick,
                bounded = true,
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = CoreRes.string.forgot_password),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.blue,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
internal fun LoginButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
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
            text = stringResource(id = CoreRes.string.login),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.white,
            fontSize = 18.sp,
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
            .height(30.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RecipeAppTheme.colors.white, RoundedCornerShape(16.dp))
            .rippleClickable(
                onClick = onClick,
                bounded = true,
            ),
    ) {
        Text(
            text = stringResource(id = CoreRes.string.sign_in_btn_register),
            textAlign = TextAlign.Center,
            color = RecipeAppTheme.colors.blue,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.Center)
        )
    }
}
