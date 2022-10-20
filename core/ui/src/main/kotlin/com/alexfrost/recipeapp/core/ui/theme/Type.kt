package com.alexfrost.recipeapp.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alexfrost.core.ui.R

val louisGeorgeCafeFamily = FontFamily(
    Font(R.font.louisgeorgecafelight, weight = FontWeight.Light),
    Font(R.font.louisgeorgecafe, weight = FontWeight.Normal),
    Font(R.font.louisgeorgecafebold, weight = FontWeight.Bold)
)

@Immutable
object LouisGeorgeCafeTypography {
    val h1: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
    val h2: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    val h3: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

    val h4: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    val large: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    val regular: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    val small: TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    val extraSmall:TextStyle =
        TextStyle(
            fontFamily = louisGeorgeCafeFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal
        )
}
