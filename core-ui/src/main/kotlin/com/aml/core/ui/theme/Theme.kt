package com.aml.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF3F5CF0),
    secondary = androidx.compose.ui.graphics.Color(0xFF7C4DFF),
    background = androidx.compose.ui.graphics.Color(0xFFF7F8FA),
    surface = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color(0xFF1F1F1F),
    onSurface = androidx.compose.ui.graphics.Color(0xFF1F1F1F),
)

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF9DB0FF),
    secondary = androidx.compose.ui.graphics.Color(0xFFCDBEFF),
    background = androidx.compose.ui.graphics.Color(0xFF0F1115),
    surface = androidx.compose.ui.graphics.Color(0xFF171A21),
    onPrimary = androidx.compose.ui.graphics.Color(0xFF0B1236),
    onBackground = androidx.compose.ui.graphics.Color(0xFFE8EAED),
    onSurface = androidx.compose.ui.graphics.Color(0xFFE8EAED),
)

@Composable
fun AmlTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
