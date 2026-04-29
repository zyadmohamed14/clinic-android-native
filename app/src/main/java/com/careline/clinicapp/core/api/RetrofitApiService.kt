package com.careline.clinicapp.core.api

import kotlinx.serialization.json.JsonElement
import retrofit2.http.*

 interface RetrofitApiService {

    @GET
    suspend fun get(
        @Url url: String,
     //   @Body body: JsonElement,
        @QueryMap(encoded = false) query: Map<String, @JvmSuppressWildcards String> = emptyMap(),
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String> = emptyMap()
    ): JsonElement

    @POST
    suspend fun post(
        @Url url: String,
        @Body body: JsonElement,
        @QueryMap(encoded = false) query: Map<String, @JvmSuppressWildcards String> = emptyMap(),
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String> = emptyMap()
    ): JsonElement

    @PUT
    suspend fun put(
        @Url url: String,
        @Body body: JsonElement,
        @QueryMap(encoded = false) query: Map<String, @JvmSuppressWildcards String> = emptyMap(),
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String> = emptyMap()
    ): JsonElement

    @DELETE
    suspend fun delete(
        @Url url: String,
        @QueryMap(encoded = false) query: Map<String, @JvmSuppressWildcards String> = emptyMap(),
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String> = emptyMap()
    ): JsonElement

    @PATCH
    suspend fun patch(
        @Url url: String,
        @Body body: JsonElement,
        @QueryMap(encoded = false) query: Map<String, @JvmSuppressWildcards String> = emptyMap(),
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String> = emptyMap()
    ): JsonElement
}