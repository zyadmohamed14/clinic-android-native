package com.careline.clinicapp.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.material.progressindicator.CircularProgressIndicator

sealed class AppButtonType {
    data object Filled : AppButtonType()
    data object Outlined : AppButtonType()
}

@Composable
fun AppButton(
    textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: AppButtonType = AppButtonType.Filled,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    val isClickable = enabled && !isLoading

    when (type) {
        AppButtonType.Filled -> FilledAppButton(
            textId = textId,
            onClick = onClick,
            modifier = modifier,
            isLoading = isLoading,
            enabled = isClickable,
            leadingIcon = leadingIcon,
        )

        AppButtonType.Outlined -> OutlinedAppButton(
            textId = textId,
            onClick = onClick,
            modifier = modifier,
            isLoading = isLoading,
            enabled = isClickable,
            leadingIcon = leadingIcon,
        )
    }
}

// ── Filled ────────────────────────────────────────────────────────────────────

@Composable
private fun FilledAppButton(
    textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            // Flutter: active ? primaryColor : defaultTextSecondary (gray)
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            disabledContentColor = Color.White.copy(alpha = 0.6f),
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    ) {
        AppButtonContent(
            textId =   textId,
            isLoading = isLoading,
            leadingIcon = leadingIcon,
        )
    }
}

// ── Outlined ──────────────────────────────────────────────────────────────────

@Composable
private fun OutlinedAppButton(
    textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 1.dp,
            // Flutter: active ? primaryColor : disabledColor
            color = if (enabled)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    ) {
        AppButtonContent(
            textId = textId,
            isLoading = isLoading,
            leadingIcon = leadingIcon,
        )
    }
}

// ── Shared content ────────────────────────────────────────────────────────────

@Composable
private fun AppButtonContent(
    textId: Int,
    isLoading: Boolean,
    leadingIcon: ImageVector?,
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(22.dp),
            strokeWidth = 2.5.dp,
            color = LocalContentColor.current,
        )
    } else {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp),
                )
            }
            Text(
                text = stringResource(id = textId),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}