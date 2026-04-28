package com.careline.clinicapp.feature.auth.domain.usecase

import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import com.careline.clinicapp.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.flow

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
     operator fun invoke(loginRequest: LoginRequest)  = flow {
        emit(Resource.Loading)
        try {
            val response =  authRepository.login(loginRequest)

        }catch (e: Exception){

        }

    }

}


