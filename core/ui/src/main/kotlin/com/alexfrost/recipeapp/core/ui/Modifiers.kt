package com.alexfrost.recipeapp.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    bounded: Boolean = true,
    onClick: () -> Unit
) = composed {
    clickable(
        enabled = enabled,
        onClick = onClick,
        indication = rememberRipple(bounded = bounded),
        interactionSource = remember { MutableInteractionSource() }
    )
}
