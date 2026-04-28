package com.careline.clinicapp.feature.auth.data.model


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
    @SerialName("phone") val phone: String,
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

@Serializable
data class LoginDataDto(
    @SerialName("token") val token: String,
    @SerialName("user") val user: UserDto
)
@Serializable
data class UserDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String? = null,
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("email_verified_at") val emailVerifiedAt: String? = null
)