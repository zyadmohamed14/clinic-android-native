package com.careline.clinicapp.core.theme



import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.careline.clinicapp.R

/**
 * Poppins font family.
 * Flutter used the google_fonts package — we use a downloadable font
 * resource defined in res/font/poppins.xml.
 */
val PoppinsFamily = FontFamily(
    Font(R.font.poppins, FontWeight.W300),  // light
    Font(R.font.poppins, FontWeight.W400),  // regular
    Font(R.font.poppins, FontWeight.W500),  // medium
    Font(R.font.poppins, FontWeight.W600),  // semiBold
    Font(R.font.poppins, FontWeight.W700),  // bold
    Font(R.font.poppins, FontWeight.W800),  // extraBold
)

/**
 * Typography scale.
 *
 * Flutter → Android mapping:
 *   displayLarge   → displayLarge   (48sp, bold)
 *   headlineLarge  → headlineLarge  (32sp, semiBold)
 *   titleLarge     → titleLarge     (20sp, semiBold)
 *   titleMedium    → titleMedium    (20sp, semiBold)  ← Flutter uses same size
 *   titleSmall     → titleSmall     (13sp, medium)
 *   bodyLarge      → bodyLarge      (18sp, normal)
 *   bodyMedium     → bodyMedium     (16sp, normal)
 *   bodySmall      → bodySmall      (14sp, normal)
 *   labelLarge     → labelLarge     (16sp, semiBold)
 *   labelMedium    → labelMedium    (14sp, medium)
 *   labelSmall     → labelSmall     (10sp, normal)
 *   displaySmall   → displaySmall   (10sp, light)
 *
 * NOTE: Flutter used flutter_screenutil for responsive sp values.
 * On Android, the system already scales sp with user font preferences —
 * no additional library needed.
 */
val CarLineTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 56.sp,

    ),
    headlineLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,

    ),
    titleSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,

    ),
    bodySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 16.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        lineHeight = 16.sp,
    ),
)