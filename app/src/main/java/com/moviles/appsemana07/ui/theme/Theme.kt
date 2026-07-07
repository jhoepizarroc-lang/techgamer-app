package com.moviles.appsemana07.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Black,
    primaryContainer = MainPurple,
    onPrimaryContainer = White,
    secondary = PurpleGrey80,
    onSecondary = Black,
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onBackground = White,
    onSurface = White,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = MainPurple,
    onPrimary = White,
    primaryContainer = LightPurple,
    onPrimaryContainer = White,
    secondary = DarkPurple,
    onSecondary = White,
    tertiary = Pink40,
    background = Gray,
    surface = White,
    onBackground = Black,
    onSurface = Black,
    surfaceVariant = White,
    onSurfaceVariant = DarkGray
)

@Composable
fun AppSemana07Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}