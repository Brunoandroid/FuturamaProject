package com.example.futuramaproject.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class ThemeToggle(
    val isDarkTheme: Boolean = false,
    val toggle: () -> Unit = {}
)

val LocalThemeToggle = compositionLocalOf { ThemeToggle() }

private val DarkColorScheme = darkColorScheme(
    primary = Grey80,
    secondary = Grey50,
    tertiary = DarkBlue60,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = White,
    onSurface = White,
    onPrimary = White
)

private val LightColorScheme = lightColorScheme(
    primary = Grey30,
    secondary = Grey40,
    tertiary = DarkBlue60,
    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black,
    onPrimary = White
)

@Composable
fun FuturamaProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    var isDarkTheme by remember { mutableStateOf(darkTheme) }

    val themeToggle = ThemeToggle(
        isDarkTheme = isDarkTheme,
        toggle = { isDarkTheme = !isDarkTheme }
    )

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalThemeToggle provides themeToggle) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}