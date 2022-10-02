package com.alexfrost.recipeapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColor(
    val white: Color,
    val black: Color,
    val red: Color,
    val yellow: Color,
    val primary: Color,
    val secondary: Color,
    val silver: Color
)

fun lightColors(): AppColor = AppColor(
    white = White,
    primary = Scooter,
    secondary = MineShaft,
    red = Carnation,
    black = Black,
    silver = Iron,
    yellow = LightningYellow
)

val LocalColors = staticCompositionLocalOf {
    AppColor(
        white = Color.Unspecified,
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        red = Color.Unspecified,
        black = Color.Unspecified,
        silver = Color.Unspecified,
        yellow = Color.Unspecified
    )
}

@Composable
fun RecipeAppTheme(content: @Composable () -> Unit) {
    val recipeAppColors = lightColors()

    CompositionLocalProvider(
        LocalColors provides recipeAppColors,
        content = content
    )
}

@Suppress
object RecipeAppTheme {
    val colors: AppColor
        @Composable
        get() = LocalColors.current
}
