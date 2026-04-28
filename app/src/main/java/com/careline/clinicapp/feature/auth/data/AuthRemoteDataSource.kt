package com.careline.clinicapp.feature.auth.data

import com.careline.clinicapp.core.api.ApiResponse
import com.careline.clinicapp.core.api.BaseApiServices
import com.careline.clinicapp.core.api.HttpMethod
import com.careline.clinicapp.core.constants.Endpoints
import com.careline.clinicapp.feature.auth.data.model.LoginDataDto
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class AuthRemoteDataSource @Inject constructor(
    private val api: BaseApiServices ,
    private val json: Json

) {
    suspend fun login(email: String, password: String): ApiResponse<LoginDataDto> {
        val jsonElement: JsonElement = api.request(
            method = HttpMethod.POST,
            url = Endpoints.LOGIN,
            body = mapOf("email" to email, "password" to password)
        )
        return json.decodeFromJsonElement(jsonElement)
    }
}