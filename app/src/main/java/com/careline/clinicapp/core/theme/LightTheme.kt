package com.careline.clinicapp.core.theme

import androidx.compose.material3.lightColorScheme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

 val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    secondary = Secondary,
    onSecondary = Color.White,
    background = LightBackground,
    onBackground = TextPrimary,
    surface = LightSurface,
    onSurface = TextPrimary,
    surfaceVariant = LightCard,
    onSurfaceVariant = TextSecondary,
    error = ErrorFill,
    onError = ErrorOnFill,
    errorContainer = ErrorSurface,
    outline = InputBorder,
)

