package com.careline.clinicapp.core.common

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorHandler {

    private val json = Json { ignoreUnknownKeys = true }

    fun handle(throwable: Throwable): AppFailure = when (throwable) {
        is HttpException -> {
            val code = throwable.code()
            when (code) {
                401 -> AppFailure.UnauthorizedFailure
                422 -> {
                    val message = extractValidationMessage(throwable)
                    AppFailure.ServerFailure(message = message, code = code)
                }
                else -> {
                    val message = extractServerMessage(throwable)
                    AppFailure.ServerFailure(message = message, code = code)
                }
            }
        }
        is SocketTimeoutException ->
            AppFailure.NetworkFailure("Connection timed out. Please try again.")
        is IOException ->
            AppFailure.NetworkFailure("No internet connection.")
        else ->
            AppFailure.UnknownFailure(throwable.message ?: "An unexpected error occurred.")
    }

    private fun extractServerMessage(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
                ?: return getDefaultMessage(exception.code())

            val root = json.parseToJsonElement(errorBody).jsonObject
            root["message"]?.jsonPrimitive?.content
                ?: getDefaultMessage(exception.code())

        } catch (e: Exception) {
            getDefaultMessage(exception.code())
        }
    }

    private fun extractValidationMessage(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
                ?: return getDefaultMessage(422)

            val root = json.parseToJsonElement(errorBody).jsonObject
            val messageNode = root["message"] ?: return getDefaultMessage(422)

            messageNode.jsonPrimitive.contentOrNull
                ?: messageNode.jsonObject.values.firstOrNull()
                    ?.jsonPrimitive?.contentOrNull
                ?: getDefaultMessage(422)

        } catch (e: Exception) {
            getDefaultMessage(422)
        }
    }

    private fun getDefaultMessage(code: Int): String = when (code) {
        400 -> "Invalid request."
        403 -> "You are not authorized for this action."
        404 -> "Requested data not found."
        422 -> "Invalid data submitted."
        500, 502, 503 -> "Server error. Please try again later."
        else -> "Unexpected error (HTTP $code)."
    }
}