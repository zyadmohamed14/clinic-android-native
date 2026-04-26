package com.careline.clinicapp.core.common



/**
 * A sealed class that wraps the result of any operation
 * (network call, DB query, etc.)
 *

 *
 * Usage in a ViewModel:
 *   _uiState.value = Resource.Loading
 *   when (val result = repository.getClinics()) {
 *       is Resource.Success -> _uiState.value = Resource.Success(result.data)
 *       is Resource.Error   -> _uiState.value = Resource.Error(result.message)
 *   }
 */
sealed class Resource<out T> {

    /** The operation is in progress. Shown as a loading spinner in the UI. */
    data object Loading : Resource<Nothing>()

    /** The operation completed successfully. [data] holds the result. */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * The operation failed.
     * [message] is a human-readable error for display.
     * [code] is an optional HTTP status code for programmatic handling.
     */
    data class Error(
        val message: String,
        val code: Int? = null,
    ) : Resource<Nothing>()
}

/** Convenience: run a block only when this Resource is Success */
inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

/** Convenience: run a block only when this Resource is Error */
inline fun <T> Resource<T>.onError(action: (String, Int?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message, code)
    return this
}

/** Convenience: run a block only when this Resource is Loading */
inline fun <T> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (this is Resource.Loading) action()
    return this
}