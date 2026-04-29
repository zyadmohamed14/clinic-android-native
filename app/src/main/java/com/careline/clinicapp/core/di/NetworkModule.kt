// core/di/NetworkModule.kt
package com.careline.clinicapp.core.di

import com.careline.clinicapp.BuildConfig
import com.careline.clinicapp.core.api.BaseApiServices
import com.careline.clinicapp.core.api.BaseApiServicesImpl
import com.careline.clinicapp.core.api.RetrofitApiService
import com.careline.clinicapp.core.api.interceptor.AuthInterceptor
import com.careline.clinicapp.core.api.interceptor.UnauthorizedInterceptor
import com.careline.clinicapp.core.api.model.ApiJson
import com.careline.clinicapp.core.constants.AppConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = ApiJson.instance

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        unauthorizedInterceptor: UnauthorizedInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)           // 1st: attaches token
        .addInterceptor(unauthorizedInterceptor)   // 2nd: watches 401 responses
        .addInterceptor(loggingInterceptor)        // last: logs final request+response
        .connectTimeout(AppConstants.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(AppConstants.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(AppConstants.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    /**
     * Retrofit instance — only used to create RetrofitApiService.
     * This is INTERNAL to the di package.
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF-8".toMediaType())
        )
        .build()

    /**
     * The internal Retrofit interface — only BaseApiServicesImpl uses this.
     * Nothing else in the app knows RetrofitApiService exists.
     */
    @Provides
    @Singleton
    fun provideRetrofitApiService(retrofit: Retrofit): RetrofitApiService =
        retrofit.create(RetrofitApiService::class.java)

    /**
     * THE ONLY BINDING THE REST OF THE APP SEES.
     *
     * Features inject BaseApiServices — not Retrofit, not RetrofitApiService.
     * To switch to Ktor: change this line to provide KtorApiServicesImpl instead.
     *
     * Previous bug: you were providing BaseApiServices TWICE in your module.
     * Hilt would throw a DuplicateBindings error at compile time.
     * Fixed by removing the duplicate and keeping only this one.
     */
    @Provides
    @Singleton
    fun provideBaseApiServices(
        retrofitApiService: RetrofitApiService,
        json: Json
    ): BaseApiServices = BaseApiServicesImpl(retrofitApiService, json)
}