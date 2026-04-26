package com.careline.clinicapp.core.theme



import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color






// ── Brand ─────────────────────────────────────────────────────────────────────
// Flutter: ColorsManager.primaryColor / secondaryColor
val Primary = Color(0xFF0891B2)
val Secondary = Color(0xFF005F5B)

// ── Dark mode surfaces ────────────────────────────────────────────────────────
// Flutter: ColorsManager.darkColor / secondaryDarkColor
val DarkBackground = Color(0xFF050811)
val DarkSurface = Color(0xFF0A0E19)

// ── Light mode surfaces ───────────────────────────────────────────────────────
// Flutter: ColorsManager.backgroundSurface / backgroundCard / defaultSurface
val LightBackground = Color(0xFFF5F5F5)
val LightCard = Color(0xFFECEFF1)
val LightSurface = Color(0xFFF9FAFB)
val LightSurfaceSecondary = Color(0xFFFAFAFA)

// ── Text ──────────────────────────────────────────────────────────────────────
// Flutter: ColorsManager.defaultText / defaultTextSecondary / miscellaneous
val TextPrimary = Color(0xFF000000)
val TextSecondary = Color(0xFF4B5563)
val TextSecondaryDark = Color(0xFFF5F5F5)
val TextMiscellaneous = Color(0xFF595959)

// ── Semantic: Error ───────────────────────────────────────────────────────────
// Flutter: ColorsManager.errorSurface / errorText / errorFill / errorOnFill
val ErrorSurface = Color(0xFFFEE2E2)
val ErrorText = Color(0xFF991B1B)
val ErrorFill = Color(0xFFDC2626)
val ErrorOnFill = Color(0xFFFFFFFF)

// ── Semantic: Success ─────────────────────────────────────────────────────────
// Flutter: ColorsManager.successSurface / successText / successFill
val SuccessSurface = Color(0xFFD1FAE5)
val SuccessText = Color(0xFF065F46)
val SuccessFill = Color(0xFF10B981)
val SuccessOnFill = Color(0xFFFFFFFF)

// ── Semantic: Warning ─────────────────────────────────────────────────────────
// Flutter: ColorsManager.warningSurface / warningText / warningFill
val WarningSurface = Color(0xFFFEF3C7)
val WarningText = Color(0xFFD97706)
val WarningFill = Color(0xFFF59E0B)
val WarningOnFill = Color(0xFF78350F)

// ── Semantic: Info ────────────────────────────────────────────────────────────
// Flutter: ColorsManager.infoSurface / infoText / infoFill
val InfoSurface = Color(0xFFE0EDFF)
val InfoText = Color(0xFF1D4ED8)
val InfoFill = Color(0xFF2563EB)
val InfoOnFill = Color(0xFF1E293B)

// ── Input ─────────────────────────────────────────────────────────────────────
// Flutter: ColorsManager.inputSurface / inputBorder
val InputSurface = Color(0xFFFFFFFF)
val InputBorder = Color(0xFF9CA3AF)

data class ExtraColors(
    val successFill: Color,
    val successSurface: Color,
    val successText: Color,
    val warningFill: Color,
    val warningSurface: Color,
    val warningText: Color,
    val infoFill: Color,
    val infoSurface: Color,
    val infoText: Color,
)

val LocalExtraColors = staticCompositionLocalOf {
    ExtraColors(
        successFill = SuccessFill,
        successSurface = SuccessSurface,
        successText = SuccessText,
        warningFill = WarningFill,
        warningSurface = WarningSurface,
        warningText = WarningText,
        infoFill = InfoFill,
        infoSurface = InfoSurface,
        infoText = InfoText,
    )
}
