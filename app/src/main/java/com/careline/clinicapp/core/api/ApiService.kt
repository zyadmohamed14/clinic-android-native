package com.careline.clinicapp.core.api


import com.careline.clinicapp.core.constants.Endpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface — declares every API endpoint.
 *
 * Flutter equivalent: the combination of:
 *   - Endpoints class (the paths)
 *   - ApiService.get/post/put/delete methods (the HTTP verbs)
 *   - Dio calls inside each remote data source
 *
 * In Retrofit, the interface + annotations replace all of that.
 * Retrofit generates the implementation at compile time.
 *
 * Every function returns Response<T> so we can inspect HTTP codes
 * (especially 401) before parsing the body.
 */
interface ApiService {

    // ── Auth ──────────────────────────────────────────────────────────────────

//    @POST(Endpoints.LOGIN)
//    suspend fun login(
//        @Body request: LoginRequest,
//    ): Response<LoginResponse>
//
//    @POST(Endpoints.REGISTER)
//    suspend fun register(
//        @Body request: RegisterRequest,
//    ): Response<LoginResponse>

    @POST(Endpoints.LOGOUT)
    suspend fun logout(): Response<Unit>

    // ── Clinics ───────────────────────────────────────────────────────────────

    @GET(Endpoints.ALL_CLINICS)
    suspend fun getAllClinics(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ): Response<ClinicsResponse>

    @GET(Endpoints.LATEST_CLINICS)
    suspend fun getLatestClinics(): Response<ClinicsResponse>

    @GET(Endpoints.ALL_CLINICS)
    suspend fun getNearbyClinics(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("per_page") perPage: Int = 15,
    ): Response<ClinicsResponse>

    @GET("clinicals/{id}")
    suspend fun getClinicDetail(
        @Path("id") clinicId: Int,
    ): Response<ClinicDetailResponse>

    // ── Favorites ─────────────────────────────────────────────────────────────

    @POST(Endpoints.TOGGLE_FAVORITE)
    suspend fun toggleFavorite(
        @Body request: ToggleFavoriteRequest,
    ): Response<ToggleFavoriteResponse>

    @GET(Endpoints.GET_FAVORITES)
    suspend fun getFavorites(): Response<ClinicsResponse>

    // ── Bookings ──────────────────────────────────────────────────────────────

    @POST(Endpoints.BOOK_APPOINTMENT)
    suspend fun bookAppointment(
        @Body request: BookingRequest,
    ): Response<BookingResponse>

    @GET(Endpoints.GET_BOOKINGS)
    suspend fun getBookings(): Response<BookingsListResponse>

    @POST("bookings/{id}/cancel")
    suspend fun cancelBooking(
        @Path("id") bookingId: Int,
    ): Response<Unit>

    // ── Profile ───────────────────────────────────────────────────────────────

    @GET(Endpoints.PROFILE)
    suspend fun getProfile(): Response<ProfileResponse>

    @PUT(Endpoints.UPDATE_PROFILE)
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest,
    ): Response<ProfileResponse>

    // ── Doctor slots ──────────────────────────────────────────────────────────

    @GET("doctors/{id}/slots")
    suspend fun getDoctorSlots(
        @Path("id") doctorId: Int,
        @Query("day") day: String,
    ): Response<SlotsResponse>
}