package com.careline.clinicapp.feature.auth.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Flutter equivalent: UserModel + the request body passed to Dio.post()
 */
@Serializable
data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
data class RegisterRequest(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
data class LoginResponse(
    @SerialName("token") val token: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("message") val message: String? = null,
)