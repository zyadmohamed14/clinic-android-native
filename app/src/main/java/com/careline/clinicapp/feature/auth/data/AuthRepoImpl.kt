package com.careline.clinicapp.feature.auth.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.feature.auth.data.mapper.toDomain
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import com.careline.clinicapp.feature.auth.data.model.RegisterRequest
import com.careline.clinicapp.feature.auth.domain.model.User
import com.careline.clinicapp.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepoImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun login(loginRequest: LoginRequest): User {
        val dto = remoteDataSource.login(
            email = loginRequest.email,
            password = loginRequest.password,
        )
        return dto.user.toDomain(token = dto.token)
    }

    override suspend fun googleLogin(): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun signup(registerRequest: RegisterRequest): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun reSendOtp(email: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyOtp(
        email: String,
        otp: String
    ): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(
        email: String,
        otp: String,
        newPassword: String
    ): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun sendForgotPassword(email: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }


    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }
}