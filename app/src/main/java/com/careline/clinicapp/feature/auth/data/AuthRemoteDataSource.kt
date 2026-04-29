package com.careline.clinicapp.feature.auth.data

import com.careline.clinicapp.core.api.BaseApiServices
import com.careline.clinicapp.core.api.model.HttpMethod
import com.careline.clinicapp.core.api.safeRequest
import com.careline.clinicapp.core.constants.Endpoints
import com.careline.clinicapp.feature.auth.data.model.LoginDataDto
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

class AuthRemoteDataSource @Inject constructor(
    private val api: BaseApiServices ,
    private val json: Json

) {
    suspend fun login(email: String, password: String): LoginDataDto {
        val body = json.encodeToJsonElement(LoginRequest(email, password))
        return api.safeRequest<LoginDataDto>(
            method = HttpMethod.POST,
            url = Endpoints.LOGIN,
            body = body,
            json = json
        )
    }
}

