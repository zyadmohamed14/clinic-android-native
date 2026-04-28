package com.careline.clinicapp.core.api

import javax.inject.Inject



import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement


/**
 * Retrofit implementation of BaseApiServices.
 *
 * This is the ONLY class that knows Retrofit exists.
 * If you replace Retrofit with Ktor tomorrow, you create KtorApiServicesImpl
 * and change ONE line in NetworkModule. Zero changes elsewhere.
 *
 * Why convert body: Any? to JsonElement?
 * - Retrofit's @Body must receive a type it knows how to serialize.
 * - JsonElement is understood by the kotlinx.serialization converter.
 * - We use Json.encodeToJsonElement to convert any serializable object safely.
 */
internal class BaseApiServicesImpl @Inject constructor(
    private val api: RetrofitApiService,
    private val json: Json
) : BaseApiServices {

    override suspend fun request(
        method: HttpMethod,
        url: String,
        body: Any?,
        queryParams: Map<String, Any>?,
        headers: Map<String, String>?
    ): JsonElement {

        // Null-safe defaults — Retrofit handles emptyMap() gracefully
        val safeQuery = queryParams.orEmpty()
        val safeHeaders = headers.orEmpty()

        // Convert body to JsonElement so Retrofit can serialize it
        val jsonBody = body?.let { encodeBody(it) } ?: buildJsonObject {}

        return when (method) {
            HttpMethod.GET -> api.get(
                url = url,
                body = jsonBody,
                query = safeQuery,
                headers = safeHeaders
            )

            HttpMethod.POST -> api.post(
                url = url,
                body = jsonBody,
                query = safeQuery,
                headers = safeHeaders
            )

            HttpMethod.PUT -> api.put(
                url = url,
                body = jsonBody,
                query = safeQuery,
                headers = safeHeaders
            )

            HttpMethod.DELETE -> api.delete(
                url = url,
                query = safeQuery,
                headers = safeHeaders
            )

            HttpMethod.PATCH -> api.patch(
                url = url,
                body = jsonBody,
                query = safeQuery,
                headers = safeHeaders
            )
        }
    }

    /**
     * Converts any object to JsonElement.
     * Handles Maps (common for request bodies) and @Serializable data classes.
     */
    @Suppress("UNCHECKED_CAST")
    private fun encodeBody(body: Any): JsonElement {
        return when (body) {
            is JsonElement -> body
            is Map<*, *> -> {
                val stringMap = (body as Map<String, Any>)
                json.parseToJsonElement(json.encodeToString(stringMap))
            }
            else -> {
                // For @Serializable data classes
                json.parseToJsonElement(json.encodeToString(body))
            }
        }
    }
}