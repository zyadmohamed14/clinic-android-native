package com.careline.clinicapp.core.api

import com.careline.clinicapp.core.api.model.HttpMethod
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
class BaseApiServicesImpl @Inject constructor(
    private val api: RetrofitApiService,
    private val json: Json
) : BaseApiServices {

    override suspend fun request(
        method: HttpMethod,
        url: String,
        body: JsonElement?,
        queryParams: Map<String, String>?,
        headers: Map<String, String>?
    ): JsonElement {

        val safeQuery = queryParams.orEmpty()
        val safeHeaders = headers.orEmpty()
        val jsonBody = body ?: buildJsonObject {}

        return when (method) {
            HttpMethod.GET -> api.get(
                url = url,
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
}