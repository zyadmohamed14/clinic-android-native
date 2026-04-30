package com.careline.clinicapp.core.ui.components


import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.R
import com.careline.clinicapp.core.ants.AppStrings

/*
 * WHY WRAPPER COMPOSABLES INSTEAD OF FACTORY METHODS?
 *
 * Flutter used extension methods on AppTextField to create
 * pre-configured instances: AppTextFieldFactory.email(...).
 *
 * In Compose, a Composable is a function — we can't return one
 * from another function in the same way Flutter can return a Widget.
 * More importantly, named Composables show up clearly in the call
 * site and in tooling (Layout Inspector, etc.).
 *
 * This approach is more readable and more Kotlin-idiomatic:
 *   EmailTextField(...)   vs   AppTextField(type = Email, ...)
 * The wrapper communicates intent at the call site.
 */

/**
 * Email-configured text field.
 * - Email keyboard (shows @ key)
 * - No auto-capitalization
 * - Next IME action (moves to next field, typically password)
 * - Email icon as leading
 */
@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: Int = AppStrings.AUTH_EMAIL,
    label: String? = "Email",
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        hint = stringResource(hint),
        modifier = modifier,
        label = label,
        isError = isError,
        errorMessage = errorMessage,
        leadingIcon = Icons.Outlined.Email,
        type = TextFieldType.Default,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = keyboardActions,
    )
}

/**
 * Password-configured text field.
 * - Password keyboard
 * - Toggleable visibility (managed internally)
 * - Done IME action by default (last field in a form)
 * - Lock icon as leading
 */
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: Int = AppStrings.AUTH_PASSWORD,
    label: String? = "Password",
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        hint = stringResource(hint),
        modifier = modifier,
        label = label,
        isError = isError,
        errorMessage = errorMessage,
        leadingIcon = Icons.Outlined.Lock,
        type = TextFieldType.Password,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions,
    )
}

/**
 * Search-configured text field.
 * - Search keyboard
 * - Search IME action
 * - Auto-clears when the X is tapped (fires onValueChange(""))
 * - Search icon as leading
 * - No label — search fields typically don't have floating labels
 */
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search",
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        hint = hint,
        modifier = modifier,
        label = null,
        leadingIcon = Icons.Outlined.Search,
        type = TextFieldType.Search,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = keyboardActions,
    )
}

/**
 * Multi-line text area.
 * - Multiline keyboard
 * - No IME action button (Enter creates new line)
 * - maxLines configurable by caller
 */
@Composable
fun TextAreaField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Type here...",
    label: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    maxLines: Int = 4,
    enabled: Boolean = true,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        hint = hint,
        modifier = modifier,
        label = label,
        isError = isError,
        errorMessage = errorMessage,
        type = TextFieldType.Default,
        enabled = enabled,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Default,
        ),
    )
}