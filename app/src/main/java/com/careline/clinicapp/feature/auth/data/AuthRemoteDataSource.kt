package com.careline.clinicapp.feature.auth.data

import com.careline.clinicapp.core.api.ApiResponse
import com.careline.clinicapp.core.api.BaseApiServices
import com.careline.clinicapp.core.api.HttpMethod
import com.careline.clinicapp.core.constants.Endpoints
import com.careline.clinicapp.feature.auth.data.model.LoginDataDto
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

class AuthRemoteDataSource @Inject constructor(
    private val api: BaseApiServices ,
    private val json: Json

) {
    suspend fun login(email: String, password: String): LoginDataDto {
//        val request = LoginRequest(
//            email = email,
//            password = password
//        )
        val request = json.encodeToJsonElement(LoginRequest(email, password))
        val jsonElement = api.request(
            method = HttpMethod.POST,
            url = Endpoints.LOGIN,
            body = request
        )
        val response: ApiResponse<LoginDataDto> = json.decodeFromJsonElement(jsonElement)

        // Server responded 200 but data is null — shouldn't happen,
        // but guard anyway
        return response.data
            ?: throw IllegalStateException(
                response.message.ifBlank { "Login failed: empty response data" }
            )
    }
}