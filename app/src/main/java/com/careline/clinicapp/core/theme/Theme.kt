package com.careline.clinicapp.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

val MaterialTheme.extraColors: ExtraColors
    @Composable get() = LocalExtraColors.current
@Composable
fun CarLineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
   // val typography = if (darkTheme) DarkTypography else LightTypography
    CompositionLocalProvider(
        LocalExtraColors provides ExtraColors(
            successFill = SuccessFill,
            successSurface = SuccessSurface,
            successText = SuccessText,
            warningFill = WarningFill,
            warningSurface = WarningSurface,
            warningText = WarningText,
            infoFill = InfoFill,
            infoSurface = InfoSurface,
            infoText = InfoText,
        ),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CarLineTypography,
            shapes = CarLineShapes,
            content = content,
        )
    }
}