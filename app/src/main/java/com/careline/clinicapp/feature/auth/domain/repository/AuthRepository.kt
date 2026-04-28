package com.careline.clinicapp.feature.auth.domain.repository

import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import com.careline.clinicapp.feature.auth.data.model.RegisterRequest
import com.careline.clinicapp.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login (loginRequest: LoginRequest) : Flow<Resource<User>>
    suspend fun googleLogin(): Flow<Resource<User>>
    suspend fun signup (registerRequest: RegisterRequest) : Flow<Resource<User>>


    suspend fun reSendOtp(
        email: String
    ): Flow<Resource<Unit>>

    suspend fun verifyOtp(
        email: String,
        otp: String
    ): Flow<Resource<User>>

    suspend fun resetPassword(
        email: String,
        otp: String,
        newPassword: String
    ): Flow<Resource<Unit>>

    suspend  fun sendForgotPassword(
        email: String
    ): Flow<Resource<Unit>>

    suspend fun logout()

    suspend fun deleteAccount()

}