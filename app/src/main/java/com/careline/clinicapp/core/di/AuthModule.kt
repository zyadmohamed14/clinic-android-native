package com.careline.clinicapp.core.di



import com.careline.clinicapp.core.api.BaseApiServices
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.core.datastore.AppDataStore
import com.careline.clinicapp.feature.auth.data.AuthRemoteDataSource
import com.careline.clinicapp.feature.auth.data.AuthRepoImpl

import com.careline.clinicapp.feature.auth.domain.repository.AuthRepository
import com.careline.clinicapp.feature.auth.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthEventBus(): AuthEventBus = AuthEventBus()
    // -----------------------------
    // Remote Data Source
    // -----------------------------
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        api: BaseApiServices,
        json: Json
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(
            api = api,
            json = json
        )
    }

    // -----------------------------
    // Repository
    // -----------------------------
    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepoImpl(remoteDataSource)
    }

    // -----------------------------
    // Use Cases
    // -----------------------------
    @Provides
    fun provideLoginUseCase(
        repository: AuthRepository,
        dataStore: AppDataStore
    ): LoginUseCase {
        return LoginUseCase(
            repository = repository,
            dataStore = dataStore
        )
    }
}