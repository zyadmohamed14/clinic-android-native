package com.careline.clinicapp.core.common


/**
 * Every possible failure type in one sealed class.
 * The compiler forces you to handle all cases — no silent misses.
 *
 * Flutter equivalent: the Failure sealed class in core/errors/failures.dart
 *
 * ServerFailure   → Flutter's ServerFailure
 * NetworkFailure  → Flutter's NetworkFailure
 * Unauthorized    → Flutter's UnauthorizedFailure
 * ParseFailure    → no direct equivalent (Flutter crashes on parse errors)
 * Unknown         → Flutter's UnexpectedFailure
 */
sealed class AppFailure {

    /** 4xx / 5xx from server with a readable message */
    data class ServerFailure(
        val message: String,
        val code: Int? = null
    ) : AppFailure()

    /** No internet, DNS failure, timeout */
    data class NetworkFailure(
        val message: String
    ) : AppFailure()

    /** 401 — token missing or expired */
    data object UnauthorizedFailure : AppFailure()

    /** Response arrived but JSON parsing failed */
    data class ParseFailure(
        val message: String
    ) : AppFailure()

    /** Anything not covered above */
    data class UnknownFailure(
        val message: String
    ) : AppFailure()

    /**
     * Resolves to a display-ready string.
     * Call this in your ViewModel or Composable — never in the Repository.
     *
     * Flutter equivalent: failure.message in the state classes
     */
    fun userMessage(): String = when (this) {
        is ServerFailure      -> message
        is NetworkFailure     -> message
        is UnauthorizedFailure -> "Your session has expired. Please log in again."
        is ParseFailure       -> "Something went wrong. Please try again."
        is UnknownFailure     -> message
    }
}