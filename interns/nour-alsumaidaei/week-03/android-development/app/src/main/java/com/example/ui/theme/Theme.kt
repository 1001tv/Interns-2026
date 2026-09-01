package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Strict light color scheme: No dark backgrounds anywhere in this app
private val LightColorScheme = lightColorScheme(
    primary = PastelBlueAccent,
    onPrimary = SurfaceCard,
    primaryContainer = PastelBlueBg,
    onPrimaryContainer = PastelBlueAccent,
    secondary = PastelMintAccent,
    onSecondary = SurfaceCard,
    secondaryContainer = PastelMintBg,
    onSecondaryContainer = PastelMintAccent,
    tertiary = PastelCoralAccent,
    onTertiary = SurfaceCard,
    tertiaryContainer = PastelCoralBg,
    onTertiaryContainer = PastelCoralAccent,
    background = BgCanvas,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceSubtle,
    onSurfaceVariant = TextSecondary,
    outline = DividerColor,
    outlineVariant = SurfaceSubtle
)

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    // Explicitly enforce clean light theme with soft background
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}
