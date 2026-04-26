package com.careline.clinicapp.core.di

import com.careline.clinicapp.core.datastore.AppDataStore
import com.careline.clinicapp.core.datastore.AppDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppDataStore(
        impl: AppDataStoreImpl,
    ): AppDataStore
}