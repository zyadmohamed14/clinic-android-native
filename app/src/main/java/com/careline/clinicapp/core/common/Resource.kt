// core/common/Resource.kt
package com.careline.clinicapp.core.common

/**
 * Wraps the result of any operation (network, DB, cache).
 *
 * Why AppFailure instead of String in Error?
 * - The ViewModel can check failure TYPE, not message content.
 * - Example: is AppFailure.UnauthorizedFailure → navigate to login.
 * - The UI decides how to display it — Resource never formats strings.
 *
 * Flutter equivalent:
 *   Loading  → AuthLoading state in auth_state.dart
 *   Success  → AuthAuthenticated / LoginSuccess
 *   Error    → AuthFailure(message) — but we do it better with types
 */
sealed class Resource<out T> {

    /** Operation in progress — show a spinner */
    data object Loading : Resource<Nothing>()

    /** Operation succeeded — [data] holds the result */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Operation failed.
     * [failure] is a typed AppFailure — never a raw String.
     *
     * In your ViewModel:
     *   is AppFailure.UnauthorizedFailure → navigate to login
     *   is AppFailure.NetworkFailure      → show "no internet" UI
     *   is AppFailure.ServerFailure       → show failure.message
     */
    data class Error(val failure: AppFailure) : Resource<Nothing>()
}

// ─── Extension functions — your idea, kept as-is ─────────────────────────────

/** Run [action] only when Success. Chainable. */
inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

/** Run [action] only when Error. Chainable. */
inline fun <T> Resource<T>.onError(action: (AppFailure) -> Unit): Resource<T> {
    if (this is Resource.Error) action(failure)
    return this
}

/** Run [action] only when Loading. Chainable. */
inline fun <T> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (this is Resource.Loading) action()
    return this
}