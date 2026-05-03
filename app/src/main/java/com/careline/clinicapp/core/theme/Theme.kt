package com.careline.clinicapp.core.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.careline.clinicapp.feature.settings.SettingsViewModel

val MaterialTheme.extraColors: ExtraColors
    @Composable get() = LocalExtraColors.current
@SuppressLint("LocalContextConfigurationRead")
@Composable
fun CarLineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
   // val typography = if (darkTheme) DarkTypography else LightTypography
    val layoutDirection = if (
        LocalContext.current.resources.configuration.layoutDirection
        == android.util.LayoutDirection.RTL
    ) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
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