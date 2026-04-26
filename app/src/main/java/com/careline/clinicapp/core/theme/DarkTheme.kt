package com.careline.clinicapp.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

 val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Secondary,
    onSecondary = Color.White,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    surfaceVariant = DarkSurface,
    onSurfaceVariant = TextSecondaryDark,
    error = ErrorFill,
    onError = ErrorOnFill,
    errorContainer = ErrorSurface,
    outline = Color(0xFF374151),   // grey[700] from dark_theme.dart
)