package com.careline.clinicapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DragIndicator
import androidx.compose.material.icons.rounded.NightlightRound
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 * A draggable dev-only overlay rendered on top of every screen.
 *
 * Flutter equivalent:
 *   MaterialApp(
 *     builder: kDebugMode
 *       ? (context, child) => DevToolsOverlay(child: child!)
 *       : null,
 *   )
 *
 * Android equivalent:
 *   CarLineTheme {
 *       DevToolsOverlay(
 *           isDark   = isDark,
 *           isArabic = isArabic,
 *           onToggleTheme    = { ... },
 *           onToggleLanguage = { ... },
 *       ) {
 *           NavGraph()
 *       }
 *   }
 *
 * In release builds this Composable renders only [content] — zero overhead.
 */
@Composable
fun DevToolsOverlay(
    // Current state — driven by your SettingsViewModel later
    isDark: Boolean,
    isArabic: Boolean,
    onToggleTheme: () -> Unit,
    onToggleLanguage: () -> Unit,
    // The rest of the app sits here — same as Flutter's `child` param
    content: @Composable () -> Unit,
) {
    // In release builds: just render content, no overlay, no overhead
    if (!isDebugBuild()) {
        content()
        return
    }

    // Track screen size so we can clamp drag position inside bounds
    var screenWidthPx by remember { mutableFloatStateOf(0f) }
    var screenHeightPx by remember { mutableFloatStateOf(0f) }

    // Panel's own rendered size — so we clamp before going off-screen
    var panelWidthPx by remember { mutableFloatStateOf(200f) }
    var panelHeightPx by remember { mutableFloatStateOf(80f) }

    val density = LocalDensity.current

    // Starting position: top = 120.dp, left = 16.dp
    // Stored as raw px for smooth drag math
    var offsetX by remember {
        mutableFloatStateOf(with(density) { 16.dp.toPx() })
    }
    var offsetY by remember {
        mutableFloatStateOf(with(density) { 120.dp.toPx() })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coords ->
                screenWidthPx = coords.size.width.toFloat()
                screenHeightPx = coords.size.height.toFloat()
            },
    ) {
        // ── App content fills the full Box ────────────────────────────────────
        content()

        // ── Draggable dev panel sits on top ───────────────────────────────────
        DevPanel(
            isDark = isDark,
            isArabic = isArabic,
            onToggleTheme = onToggleTheme,
            onToggleLanguage = onToggleLanguage,
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .onGloballyPositioned { coords ->
                    panelWidthPx = coords.size.width.toFloat()
                    panelHeightPx = coords.size.height.toFloat()
                }
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        // Clamp so the panel never leaves the screen
                        offsetX = (offsetX + dragAmount.x)
                            .coerceIn(0f, screenWidthPx - panelWidthPx)
                        offsetY = (offsetY + dragAmount.y)
                            .coerceIn(0f, screenHeightPx - panelHeightPx)
                    }
                },
        )
    }
}

// ── The visual panel ──────────────────────────────────────────────────────────

@Composable
private fun DevPanel(
    isDark: Boolean,
    isArabic: Boolean,
    onToggleTheme: () -> Unit,
    onToggleLanguage: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp)),
        color = if (isDark)
            Color(0xFF374151).copy(alpha = 0.95f)   // grey[700] dark panel
        else
            Color.White.copy(alpha = 0.95f),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Drag handle — visual affordance only, not interactive itself
            Icon(
                imageVector = Icons.Rounded.DragIndicator,
                contentDescription = "Drag",
                tint = if (isDark) Color.White.copy(alpha = 0.4f)
                else Color.Black.copy(alpha = 0.25f),
            )

            Spacer(modifier = Modifier.width(4.dp))

            // Theme toggle
            DevPillButton(
                icon = if (isDark) Icons.Rounded.WbSunny
                else Icons.Rounded.NightlightRound,
                label = if (isDark) "Light" else "Dark",
                color = if (isDark) Color(0xFFFBBF24) else Color(0xFF6366F1),
                onClick = onToggleTheme,
            )

            Spacer(modifier = Modifier.width(6.dp))

            // Language toggle
            DevPillButton(
                icon = Icons.Rounded.Translate,
                label = if (isArabic) "EN" else "AR",
                color = Color(0xFF0891B2),   // primary teal
                onClick = onToggleLanguage,
            )
        }
    }
}

// ── Pill button ───────────────────────────────────────────────────────────────

@Composable
private fun DevPillButton(
    icon: ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = color.copy(alpha = 0.15f),
        contentColor = color,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.padding(end = 4.dp),
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
        }
    }
}

// ── Release guard ─────────────────────────────────────────────────────────────

/**
 * Returns true only in debug builds.
 *
 * Flutter equivalent: kDebugMode
 * In Kotlin, BuildConfig.DEBUG is the exact equivalent — it's false
 * in release/production builds automatically.
 */
private fun isDebugBuild(): Boolean = com.careline.clinicapp.BuildConfig.DEBUG
