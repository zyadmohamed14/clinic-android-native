package com.careline.clinicapp.core.api


import com.careline.clinicapp.BuildConfig
import com.careline.clinicapp.core.api.interceptor.AuthInterceptor
import com.careline.clinicapp.core.api.interceptor.UnauthorizedInterceptor
import com.careline.clinicapp.core.constants.AppConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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

/**
 * Provides the entire networking stack via Hilt.
 *
 * Flutter equivalent: DioClient.getDio() + _setupInterceptors() +
 * the ApiService constructor in api_service.dart
 *
 * Build order (each depends on the one above):
 *   Json → AuthInterceptor → UnauthorizedInterceptor
 *       → OkHttpClient → Retrofit → ApiService
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Kotlinx Serialization JSON instance.
     * ignoreUnknownKeys = true → won't crash if API adds new fields
     * isLenient = true → handles minor malformations
     */
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    /**
     * OkHttp logging — only active in debug builds.
     * Flutter equivalent: PrettyDioLogger interceptor
     */
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

    /**
     * OkHttpClient — the actual HTTP engine.
     * Flutter equivalent: the Dio instance with BaseOptions.
     *
     * Interceptor order matters:
     *   1. Logging (outermost — sees the final request)
     *   2. Auth (adds the token)
     *   3. Unauthorized (watches the response)
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        unauthorizedInterceptor: UnauthorizedInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .addInterceptor(unauthorizedInterceptor)
        .connectTimeout(AppConstants.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(AppConstants.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(AppConstants.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    /**
     * Retrofit — turns the ApiService interface into real HTTP calls.
     * BASE_URL comes from BuildConfig — set per flavor in build.gradle.kts.
     *
     * Flutter equivalent: Dio + BaseOptions(baseUrl: Endpoints.baseUrl)
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
            json.asConverterFactory("application/json".toMediaType()),
        )
        .build()

    /**
     * ApiService — the interface Retrofit implements automatically.
     * Flutter equivalent: the ApiService class with get/post/put/delete methods.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}