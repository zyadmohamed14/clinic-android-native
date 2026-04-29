package com.careline.clinicapp.feature.auth.domain.usecase

import android.util.Log
import com.careline.clinicapp.core.common.ErrorHandler
import com.careline.clinicapp.core.common.Resource
import com.careline.clinicapp.core.datastore.AppDataStore
import com.careline.clinicapp.feature.auth.data.model.LoginRequest
import com.careline.clinicapp.feature.auth.domain.model.User
import com.careline.clinicapp.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val dataStore: AppDataStore,
    private val json: Json
) {
    operator fun invoke(request: LoginRequest): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        try {
            val user = repository.login(request)
            // Persist token immediately after successful login
            dataStore.saveAuthToken(user.token)
            dataStore.saveUserData(json.encodeToString(user))
            emit(Resource.Success(user))
        } catch (throwable: Throwable,) {
            val root = throwable.rootCause()
            Log.e("ErrorHandler", "Root cause: ${root.message}", root)
            // ErrorHandler maps every possible Throwable to a typed AppFailure.
            // No feature-specific exception handling needed — ever.
            emit(Resource.Error(ErrorHandler.handle(throwable)))
        }
    }

}
fun Throwable.rootCause(): Throwable {
    var cause = this
    while (cause.cause != null) {
        cause = cause.cause!!
    }
    return cause
}
