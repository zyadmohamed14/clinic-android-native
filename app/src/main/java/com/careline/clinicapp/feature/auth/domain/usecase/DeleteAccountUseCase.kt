package com.careline.clinicapp.feature.auth.domain.usecase

import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteAccountUseCase (
    private val authRepository: AuthRepository,
    //private val userRepository: UserRepository

){
    operator fun invoke(): Flow<Resource<Unit>>  = flow{

        emit(Resource.Loading)
        try {
            // 1. Delete account from remote API
            authRepository.deleteAccount()

            // 2. Clear local user data
           // userRepository.clearUser()

            emit(Resource.Success(Unit))
        }catch (e: Exception){
          // handle Message useing ErrorHandler
        }
    }
}