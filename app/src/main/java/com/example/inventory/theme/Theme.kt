package com.example.inventory.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = pastel_light_primary,
    onPrimary = pastel_light_onPrimary,
    primaryContainer = pastel_light_primaryContainer,
    onPrimaryContainer = pastel_light_onPrimaryContainer,
    secondary = pastel_light_secondary,
    onSecondary = pastel_light_onSecondary,
    secondaryContainer = pastel_light_secondaryContainer,
    onSecondaryContainer = pastel_light_onSecondaryContainer,
    tertiary = pastel_light_tertiary,
    onTertiary = pastel_light_onTertiary,
    tertiaryContainer = pastel_light_tertiaryContainer,
    onTertiaryContainer = pastel_light_onTertiaryContainer,
    error = Color(0xFFBA1A1A), // Rojo para errores
    errorContainer = Color(0xFFFFDAD6), // Fondo para errores
    onError = Color(0xFFFFFFFF), // Texto blanco sobre error
    onErrorContainer = Color(0xFF410002), // Texto oscuro sobre contenedor de error
    background = pastel_light_background,
    onBackground = pastel_light_onBackground,
    surface = pastel_light_surface,
    onSurface = pastel_light_onSurface,
    surfaceVariant = Color(0xFFDDE3EA), // Variante de superficie más suave
    onSurfaceVariant = Color(0xFF41474D), // Texto oscuro sobre variante de superficie
    outline = pastel_light_outline,
    inverseOnSurface = Color(0xFFF0F0F3),
    inverseSurface = Color(0xFF2E3133),
    inversePrimary = Color(0xFF8DCDFF), // Pastel invertido
    surfaceTint = pastel_light_primary,
    outlineVariant = Color(0xFFC1C7CE),
    scrim = Color(0xFF000000)
)

private val DarkColors = darkColorScheme(
    primary = pastel_dark_primary,
    onPrimary = pastel_dark_onPrimary,
    primaryContainer = pastel_dark_primaryContainer,
    onPrimaryContainer = pastel_dark_onPrimaryContainer,
    secondary = pastel_dark_secondary,
    onSecondary = pastel_dark_onSecondary,
    secondaryContainer = pastel_dark_secondaryContainer,
    onSecondaryContainer = pastel_dark_onSecondaryContainer,
    tertiary = pastel_dark_tertiary,
    onTertiary = pastel_dark_onTertiary,
    tertiaryContainer = pastel_dark_tertiaryContainer,
    onTertiaryContainer = pastel_dark_onTertiaryContainer,
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = pastel_dark_background,
    onBackground = pastel_dark_onBackground,
    surface = pastel_dark_surface,
    onSurface = pastel_dark_onSurface,
    surfaceVariant = Color(0xFF41474D),
    onSurfaceVariant = Color(0xFFC1C7CE),
    outline = pastel_dark_outline,
    inverseOnSurface = Color(0xFF1A1C1E),
    inverseSurface = Color(0xFFE2E2E5),
    inversePrimary = pastel_light_primary,
    surfaceTint = pastel_dark_primary,
    outlineVariant = Color(0xFF41474D),
    scrim = Color(0xFF000000)
)


@Composable
fun InventoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}