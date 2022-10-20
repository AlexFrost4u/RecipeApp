package com.alexfrost.recipeapp.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.alexfrost.recipeapp.core.ui.Black
import com.alexfrost.recipeapp.core.ui.BlueRibbon
import com.alexfrost.recipeapp.core.ui.Carnation
import com.alexfrost.recipeapp.core.ui.Iron
import com.alexfrost.recipeapp.core.ui.LightningYellow
import com.alexfrost.recipeapp.core.ui.MineShaft
import com.alexfrost.recipeapp.core.ui.Scooter
import com.alexfrost.recipeapp.core.ui.White

@Immutable
data class AppColor(
    val white: Color,
    val black: Color,
    val red: Color,
    val yellow: Color,
    val primary: Color,
    val secondary: Color,
    val silver: Color,
    val blue: Color
)

@Immutable
data class AppTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val large: TextStyle,
    val regular: TextStyle,
    val small: TextStyle,
)

fun lightColors(): AppColor = AppColor(
    white = White,
    primary = Scooter,
    secondary = MineShaft,
    red = Carnation,
    black = Black,
    silver = Iron,
    yellow = LightningYellow,
    blue = BlueRibbon
)

fun louisGeorgeCafeTypography(): AppTypography = AppTypography(
    h1 = LouisGeorgeCafeTypography.h1,
    h2 = LouisGeorgeCafeTypography.h2,
    h3 = LouisGeorgeCafeTypography.h3,
    h4 = LouisGeorgeCafeTypography.h4,
    small = LouisGeorgeCafeTypography.small,
    regular = LouisGeorgeCafeTypography.regular,
    large = LouisGeorgeCafeTypography.large
)

val LocalColors = staticCompositionLocalOf {
    AppColor(
        white = Color.Unspecified,
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        red = Color.Unspecified,
        black = Color.Unspecified,
        silver = Color.Unspecified,
        yellow = Color.Unspecified,
        blue = Color.Unspecified
    )
}

val LocalTypography = staticCompositionLocalOf {
    AppTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        h4 = TextStyle.Default,
        large = TextStyle.Default,
        regular = TextStyle.Default,
        small = TextStyle.Default,
    )
}

@Composable
fun RecipeAppTheme(content: @Composable () -> Unit) {
    val recipeAppColors = lightColors()
    val recipeAppTypography = louisGeorgeCafeTypography()

    CompositionLocalProvider(
        LocalColors provides recipeAppColors,
        LocalTypography provides recipeAppTypography,
        content = content
    )
}

@Suppress
object RecipeAppTheme {
    val colors: AppColor
        @Composable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        get() = LocalTypography.current
}
