package com.careline.clinicapp.core.api

import com.careline.clinicapp.core.api.model.ApiResponse
import com.careline.clinicapp.core.api.model.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


/**
 * Unified method to handle all HTTP requests
 *
 * @param method        HTTP verb (GET, POST, PUT, DELETE, PATCH)
 * @param url           Endpoint (relative or absolute)
 * @param body          Request body (can be Map, JSON, etc.)
 * @param queryParams   Optional query parameters
 * @param headers       Optional additional headers
 */
interface BaseApiServices {
    suspend fun request(
        method: HttpMethod,
        url: String,
        body: JsonElement? = null,
        queryParams: Map<String, String>? = null,
        headers: Map<String, String>? = null
    ): JsonElement
}

suspend inline fun <reified T> BaseApiServices.safeRequest(
    method: HttpMethod,
    url: String,
    body: JsonElement? = null,
    queryParams: Map<String, String>? = null,
    json: Json
): T {
    val jsonElement = request(method, url, body, queryParams)
    val response: ApiResponse<T> = json.decodeFromJsonElement(jsonElement)
    return response.data
        ?: throw IllegalStateException(response.message.ifBlank { "Empty response" })
}