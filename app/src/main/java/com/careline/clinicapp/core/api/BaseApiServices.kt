package com.careline.clinicapp.core.api

import kotlinx.serialization.json.JsonElement


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