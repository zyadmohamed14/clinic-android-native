package com.careline.clinicapp.core.api



import com.careline.clinicapp.core.common.Resource
import kotlinx.serialization.json.Json
import retrofit2.Response

/**
 * Converts a Retrofit Response<T> into a Resource<T>.
 *
 * Flutter equivalent: ApiErrorHandler.handleDioException() +
 * the _handleResponseError() method in api_error_handler.dart
 *
 * Every repository calls safeApiCall { apiService.someEndpoint() }
 * and gets back a Resource without writing try/catch everywhere.
 */
object NetworkErrorHandler {

    /**
     * Wraps any suspend API call in try/catch and maps the result
     * to Resource.Success or Resource.Error.
     *
     * Usage in a repository:
     *   override suspend fun getClinics() = safeApiCall {
     *       apiService.getAllClinics()
     *   }
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.Success(body)
                } else {
                    Resource.Error(
                        message = "Empty response",
                        code = response.code(),
                    )
                }
            } else {
                val errorMessage = parseErrorMessage(response)
                Resource.Error(
                    message = errorMessage,
                    code = response.code(),
                )
            }
        } catch (e: java.net.SocketTimeoutException) {
            Resource.Error(message = "Connection timeout. Please try again.")
        } catch (e: java.net.UnknownHostException) {
            Resource.Error(message = "No internet connection.")
        } catch (e: java.io.IOException) {
            Resource.Error(message = "Network error. Please check your connection.")
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An unexpected error occurred.")
        }
    }

    /**
     * Parses the error body from the server response.
     *
     * Flutter equivalent: ApiErrorHandler._extractErrorMessage()
     * Handles {"message": "..."} and {"errors": {...}} formats.
     */
    private fun <T> parseErrorMessage(response: Response<T>): String {
        return try {
            val errorBody = response.errorBody()?.string()
            if (!errorBody.isNullOrBlank()) {
                val json = Json { ignoreUnknownKeys = true }
                val errorDto = json.decodeFromString<ErrorResponse>(errorBody)
                errorDto.message
                    ?: errorDto.error
                    ?: getDefaultMessage(response.code())
            } else {
                getDefaultMessage(response.code())
            }
        } catch (e: Exception) {
            getDefaultMessage(response.code())
        }
    }

    private fun getDefaultMessage(code: Int): String = when (code) {
        400 -> "Invalid request."
        401 -> "Session expired. Please log in again."
        403 -> "You are not authorized for this action."
        404 -> "Requested data not found."
        422 -> "Invalid data submitted."
        500, 502, 503 -> "Server error. Please try again later."
        else -> "Unexpected error occurred (HTTP $code)."
    }
}

@kotlinx.serialization.Serializable
private data class ErrorResponse(
    @kotlinx.serialization.SerialName("message") val message: String? = null,
    @kotlinx.serialization.SerialName("error") val error: String? = null,
)