package com.careline.clinicapp.core.api.model



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Generic paginated list response.
 * Flutter equivalent: ClinicsResponse model (clinics_response.dart)
 */

@Serializable
data class ApiResponse<T>(
    @SerialName("status") val status: Boolean = false,
    @SerialName("message") val message: String = "",
    @SerialName("data") val data: T? = null
)
@Serializable
data class ClinicsResponse(
    @SerialName("data") val data: List<ClinicSummaryDto> = emptyList(),
    @SerialName("current_page") val currentPage: Int = 1,
    @SerialName("hasMorePage") val hasMorePage: Boolean = false,
)

@Serializable
data class ClinicSummaryDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String = "",
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("specialty") val specialty: String? = null,
    @SerialName("reviews_count") val reviewsCount: Int = 0,
    @SerialName("location") val location: String = "",
    @SerialName("rating") val rating: Double = 0.0,
    @SerialName("is_open") val isOpen: Boolean = false,
    @SerialName("doctors_count") val doctorsCount: Int = 0,
    @SerialName("is_favorite") val isFavorite: Boolean = false,
)

@Serializable
data class ClinicDetailResponse(
    @SerialName("data") val data: ClinicDetailDto,
)

@Serializable
data class ClinicDetailDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String = "",
    @SerialName("specialty") val specialty: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("location") val location: String = "",
    @SerialName("full_address") val fullAddress: String = "",
    @SerialName("latitude") val latitude: Double = 0.0,
    @SerialName("longitude") val longitude: Double = 0.0,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("rating") val rating: Double = 0.0,
    @SerialName("reviews_count") val reviewsCount: Int = 0,
    @SerialName("price") val price: Double = 0.0,
    @SerialName("is_open") val isOpen: Boolean = false,
    @SerialName("opening_hours") val openingHours: String = "",
    @SerialName("services") val services: List<String> = emptyList(),
    @SerialName("facilities") val facilities: List<String> = emptyList(),
    @SerialName("insurance_accepted") val insuranceAccepted: List<String> = emptyList(),
    @SerialName("statistics") val statistics: StatisticsDto? = null,
    @SerialName("contact_info") val contactInfo: ContactInfoDto? = null,
    @SerialName("doctors") val doctors: List<DoctorDto> = emptyList(),
    @SerialName("reviews") val reviews: List<ReviewDto> = emptyList(),
)

@Serializable
data class StatisticsDto(
    @SerialName("total_visits") val totalVisits: Int = 0,
    @SerialName("total_bookings") val totalBookings: Int = 0,
    @SerialName("total_doctors") val totalDoctors: Int = 0,
    @SerialName("satisfaction_rate") val satisfactionRate: Int = 0,
)

@Serializable
data class ContactInfoDto(
    @SerialName("phone") val phone: String = "",
    @SerialName("email") val email: String = "",
    @SerialName("website") val website: String = "",
)

@Serializable
data class DoctorDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String = "",
    @SerialName("specialty") val specialty: String = "",
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("rating") val rating: Double = 0.0,
    @SerialName("experience_years") val experienceYears: Int = 0,
    @SerialName("consultation_fee") val consultationFee: Double = 0.0,
    @SerialName("available_slots") val availableSlots: List<TimeSlotDto> = emptyList(),
)

@Serializable
data class TimeSlotDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("day") val day: String = "",
    @SerialName("time_slot") val timeSlot: String = "",
    @SerialName("is_booked") val isBooked: Boolean = false,
    @SerialName("booked_count") val bookedCount: Int = 0,
    @SerialName("max_bookings") val maxBookings: Int = 5,
)

@Serializable
data class ReviewDto(
    @SerialName("id") val id: String,
    @SerialName("patient_name") val patientName: String = "",
    @SerialName("patient_image_url") val patientImageUrl: String = "",
    @SerialName("rating") val rating: Double = 0.0,
    @SerialName("comment") val comment: String = "",
    @SerialName("date") val date: String = "",
    @SerialName("doctor_name") val doctorName: String = "",
)

@Serializable
data class ToggleFavoriteRequest(
    @SerialName("clinic_id") val clinicId: Int,
)

@Serializable
data class ToggleFavoriteResponse(
    @SerialName("is_favorite") val isFavorite: Boolean,
)

@Serializable
data class BookingRequest(
    @SerialName("clinic_id") val clinicId: Int,
    @SerialName("doctor_id") val doctorId: String,
    @SerialName("slot_id") val slotId: Int,
    @SerialName("patient_name") val patientName: String,
    @SerialName("patient_phone") val patientPhone: String,
)

@Serializable
data class BookingResponse(
    @SerialName("id") val id: Int,
    @SerialName("status") val status: String = "",
)

@Serializable
data class BookingsListResponse(
    @SerialName("data") val data: List<BookingResponse> = emptyList(),
)

@Serializable
data class ProfileResponse(
    @SerialName("name") val name: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("image") val image: String? = null,
)

@Serializable
data class UpdateProfileRequest(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
)

@Serializable
data class SlotsResponse(
    @SerialName("data") val data: List<TimeSlotDto> = emptyList(),
)