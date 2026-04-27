package com.careline.clinicapp.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Core AppTextField composable.
 *
 * DESIGN DECISIONS:
 *
 * 1. STATELESS — the parent owns value + error. This component only
 *    renders what it's given and fires events upward. The single
 *    exception is password visibility, which is a UI concern only.
 *
 * 2. INTERACTION SOURCE — we use MutableInteractionSource to observe
 *    focus state internally. This is how Compose does what Flutter's
 *    FocusNode did — but we don't expose it to the parent because
 *    focus is a rendering concern, not a business concern.
 *
 * 3. TRAILING ICON PRIORITY — same logic as Flutter:
 *    - If type == Password → show eye toggle (overrides everything)
 *    - If type == Search && value is not empty → show clear X
 *    - Otherwise → show whatever trailingIcon the caller passed in
 *    This keeps the caller's API simple.
 *
 * 4. NO VALIDATOR LAMBDA — validation belongs in the ViewModel.
 *    The parent passes errorMessage: String? which is null when valid.
 *    This is cleaner and makes the ViewModel unit-testable.
 *
 * @param value         Current text — owned by the caller/ViewModel.
 * @param onValueChange Emitted on every keystroke. Parent updates state.
 * @param hint          Placeholder text shown when field is empty.
 * @param modifier      Standard Compose modifier — caller controls layout.
 * @param label         Floating label above the field (optional).
 * @param isError       True when the current value fails validation.
 * @param errorMessage  Shown below the field when isError is true.
 * @param leadingIcon   Icon shown on the start side.
 * @param trailingIcon  Icon shown on the end side. May be overridden by
 *                      type-specific behavior (see TRAILING ICON PRIORITY).
 * @param type          Controls keyboard, visual transformation, and trailing icon.
 * @param keyboardOptions Passed directly to the underlying TextField.
 * @param keyboardActions Passed directly to the underlying TextField.
 * @param maxLines      Max visible lines. Use Int.MAX_VALUE for text areas.
 * @param enabled       Disables all interaction when false.
 * @param readOnly      Field is visible but not editable.
 */
sealed class TextFieldType {
    //Default text input — no special behavior.

    data object Default : TextFieldType()

    data object Password : TextFieldType()

    data object Search : TextFieldType()
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    type: TextFieldType = TextFieldType.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1,
    enabled: Boolean = true,
    readOnly: Boolean = false,
){
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val visualTransformation = when {
        type is TextFieldType.Password && !passwordVisible ->
            PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    val resolvedTrailingIcon: @Composable (() -> Unit)? = when {
        type is TextFieldType.Password -> {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Outlined.VisibilityOff
                        else
                            Icons.Outlined.Visibility,
                        contentDescription = if (passwordVisible)
                            "Hide password"
                        else
                            "Show password"
                    )
                }
            }
        }

        type is TextFieldType.Search && value.isNotEmpty() -> {
            {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Clear search"
                    )
                }
            }
        }

        trailingIcon != null -> {
            { Icon(imageVector = trailingIcon, contentDescription = null) }
        }

        else -> null
    }

    val colors = OutlinedTextFieldDefaults.colors(
        // Text inside the field
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),

        // Hint / placeholder
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,

        // Label
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),

        // Border
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        errorBorderColor = MaterialTheme.colorScheme.error,

//        // Container (background of the field itself)
//        focusedContainerColor = MaterialTheme.colorScheme.surface,
//        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//        disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.38f),

        // Icons
        focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,

        // Cursor
        cursorColor = MaterialTheme.colorScheme.primary,

        // Error states
        errorTextColor = MaterialTheme.colorScheme.onSurface,
        errorLabelColor = MaterialTheme.colorScheme.error,
        errorLeadingIconColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        errorCursorColor = MaterialTheme.colorScheme.error,
    )
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { label?.let { Text(text = it) } },
            placeholder = { Text(text = hint) },
            leadingIcon = leadingIcon?.let {
                { Icon(imageVector = it, contentDescription = null) }
            },
            trailingIcon = resolvedTrailingIcon,
            isError = isError,
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            enabled = enabled,
            readOnly = readOnly,
            colors = colors,
            interactionSource = interactionSource,
            shape = MaterialTheme.shapes.medium,
        )

        AnimatedVisibility(
            visible = isError && errorMessage != null,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            errorMessage?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
    }
