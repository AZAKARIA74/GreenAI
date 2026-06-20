package com.greenai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.greenai.ui.theme.ColorBackground
import com.example.greenai.ui.theme.ColorError
import com.example.greenai.ui.theme.ColorErrorContainer
import com.example.greenai.ui.theme.ColorOnBackground
import com.example.greenai.ui.theme.ColorOnError
import com.example.greenai.ui.theme.ColorOnErrorContainer
import com.example.greenai.ui.theme.ColorOnPrimary
import com.example.greenai.ui.theme.ColorOnPrimaryContainer
import com.example.greenai.ui.theme.ColorOnSecondary
import com.example.greenai.ui.theme.ColorOnSecondaryContainer
import com.example.greenai.ui.theme.ColorOnSurface
import com.example.greenai.ui.theme.ColorOnSurfaceVariant
import com.example.greenai.ui.theme.ColorOnTertiary
import com.example.greenai.ui.theme.ColorOnTertiaryContainer
import com.example.greenai.ui.theme.ColorOutline
import com.example.greenai.ui.theme.ColorOutlineVariant
import com.example.greenai.ui.theme.ColorPrimary
import com.example.greenai.ui.theme.ColorPrimaryContainer
import com.example.greenai.ui.theme.ColorSecondary
import com.example.greenai.ui.theme.ColorSecondaryContainer
import com.example.greenai.ui.theme.ColorSurface
import com.example.greenai.ui.theme.ColorSurfaceVariant
import com.example.greenai.ui.theme.ColorTertiary
import com.example.greenai.ui.theme.ColorTertiaryContainer
import com.example.greenai.ui.theme.DarkAppBarButton
import com.example.greenai.ui.theme.DarkColorBackground
import com.example.greenai.ui.theme.DarkColorError
import com.example.greenai.ui.theme.DarkColorErrorContainer
import com.example.greenai.ui.theme.DarkColorOnBackground
import com.example.greenai.ui.theme.DarkColorOnError
import com.example.greenai.ui.theme.DarkColorOnErrorContainer
import com.example.greenai.ui.theme.DarkColorOnPrimary
import com.example.greenai.ui.theme.DarkColorOnPrimaryContainer
import com.example.greenai.ui.theme.DarkColorOnSecondary
import com.example.greenai.ui.theme.DarkColorOnSecondaryContainer
import com.example.greenai.ui.theme.DarkColorOnSurface
import com.example.greenai.ui.theme.DarkColorOnSurfaceVariant
import com.example.greenai.ui.theme.DarkColorOnTertiary
import com.example.greenai.ui.theme.DarkColorOnTertiaryContainer
import com.example.greenai.ui.theme.DarkColorOutline
import com.example.greenai.ui.theme.DarkColorOutlineVariant
import com.example.greenai.ui.theme.DarkColorPrimary
import com.example.greenai.ui.theme.DarkColorPrimaryContainer
import com.example.greenai.ui.theme.DarkColorSecondary
import com.example.greenai.ui.theme.DarkColorSecondaryContainer
import com.example.greenai.ui.theme.DarkColorSurface
import com.example.greenai.ui.theme.DarkColorSurfaceVariant
import com.example.greenai.ui.theme.DarkColorTertiary
import com.example.greenai.ui.theme.DarkColorTertiaryContainer
import com.example.greenai.ui.theme.GreenAIShapes
import com.example.greenai.ui.theme.LightAppBarButton

// ─────────────────────────────────────────────
//  Light color scheme
// ─────────────────────────────────────────────
private val LightColorScheme = lightColorScheme(
    primary              = ColorPrimary,
    onPrimary            = ColorOnPrimary,
    primaryContainer     = ColorPrimaryContainer,
    onPrimaryContainer   = ColorOnPrimaryContainer,
    inverseSurface = LightAppBarButton,

    secondary            = ColorSecondary,
    onSecondary          = ColorOnSecondary,
    secondaryContainer   = ColorSecondaryContainer,
    onSecondaryContainer = ColorOnSecondaryContainer,

    tertiary             = ColorTertiary,
    onTertiary           = ColorOnTertiary,
    tertiaryContainer    = ColorTertiaryContainer,
    onTertiaryContainer  = ColorOnTertiaryContainer,

    error                = ColorError,
    onError              = ColorOnError,
    errorContainer       = ColorErrorContainer,
    onErrorContainer     = ColorOnErrorContainer,

    background           = ColorBackground,
    onBackground         = ColorOnBackground,
    surface              = ColorSurface,
    onSurface            = ColorOnSurface,
    surfaceVariant       = ColorSurfaceVariant,
    onSurfaceVariant     = ColorOnSurfaceVariant,
    outline              = ColorOutline,
    outlineVariant       = ColorOutlineVariant,
)

// ─────────────────────────────────────────────
//  Dark color scheme
// ─────────────────────────────────────────────
private val DarkColorScheme = darkColorScheme(
    primary              = DarkColorPrimary,
    onPrimary            = DarkColorOnPrimary,
    primaryContainer     = DarkColorPrimaryContainer,
    onPrimaryContainer   = DarkColorOnPrimaryContainer,

    inverseSurface = DarkAppBarButton,
    secondary            = DarkColorSecondary,
    onSecondary          = DarkColorOnSecondary,
    secondaryContainer   = DarkColorSecondaryContainer,
    onSecondaryContainer = DarkColorOnSecondaryContainer,

    tertiary             = DarkColorTertiary,
    onTertiary           = DarkColorOnTertiary,
    tertiaryContainer    = DarkColorTertiaryContainer,
    onTertiaryContainer  = DarkColorOnTertiaryContainer,

    error                = DarkColorError,
    onError              = DarkColorOnError,
    errorContainer       = DarkColorErrorContainer,
    onErrorContainer     = DarkColorOnErrorContainer,

    background           = DarkColorBackground,
    onBackground         = DarkColorOnBackground,
    surface              = DarkColorSurface,
    onSurface            = DarkColorOnSurface,
    surfaceVariant       = DarkColorSurfaceVariant,
    onSurfaceVariant     = DarkColorOnSurfaceVariant,
    outline              = DarkColorOutline,
    outlineVariant       = DarkColorOutlineVariant,
)

// ─────────────────────────────────────────────
//  GreenAI theme entry point
// ─────────────────────────────────────────────
@Composable
fun GreenAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = GreenAITypography,
        shapes      = GreenAIShapes,
        content     = content,
    )
}