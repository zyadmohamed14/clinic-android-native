package com.careline.clinicapp.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/*
*   extraSmall → 4dp  (chips, small tags)
*   small      → 8dp  (input fields, small cards)
*   medium     → 12dp (buttons, cards)
*   large      → 16dp (bottom sheets, large cards)
*   extraLarge → 24dp (featured cards, dialogs)
*/
val CarLineShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)