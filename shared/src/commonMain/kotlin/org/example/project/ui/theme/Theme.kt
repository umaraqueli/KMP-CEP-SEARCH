package org.example.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrownLight,
    secondary = BrownLight,
    tertiary = BrownLight,
    primaryContainer = BeigeContainerLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    onPrimary = Color.White,
    onPrimaryContainer = OnSurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = BrownDark,
    secondary = BrownDark,
    tertiary = BrownDark,
    primaryContainer = BrownContainerDark,
    background = SurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    onPrimary = Color(0xFF3D2921),
    onPrimaryContainer = OnSurfaceDark
)

@Composable
fun ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
