package com.careline.clinicapp.core.api.model

import kotlinx.serialization.json.Json

// core/api/ApiJson.kt — single source of truth
object ApiJson {
    val instance: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
        explicitNulls = false
    }
}