package com.careline.clinicapp.core.ants





import androidx.annotation.StringRes
import com.careline.clinicapp.R


/**
 * Centralized object that holds all string resource IDs
 * Best practice for Jetpack Compose + Android
 */
object AppStrings {

    // ==================== AUTH GUARD ====================
    @StringRes  val AUTH_GUARD_TITLE = R.string.auth_guard_title
    @StringRes  val AUTH_GUARD_MESSAGE = R.string.auth_guard_message
    @StringRes  val AUTH_GUARD_LATER = R.string.auth_guard_later

    // ==================== AUTH ====================
    @StringRes  val AUTH_USER = R.string.auth_user
    @StringRes  val AUTH_LOGIN = R.string.auth_login
    @StringRes  val AUTH_SIGNUP = R.string.auth_signup
    @StringRes  val AUTH_EMAIL = R.string.auth_email
    @StringRes  val AUTH_PASSWORD = R.string.auth_password
    @StringRes  val AUTH_CONFIRM_PASSWORD = R.string.auth_confirm_password
    @StringRes  val AUTH_FULL_NAME = R.string.auth_full_name
    @StringRes  val AUTH_PHONE = R.string.auth_phone
    @StringRes  val AUTH_CONTINUE_AS_GUEST = R.string.auth_continue_as_guest
    @StringRes  val AUTH_CREATE_ACCOUNT = R.string.auth_create_account
    @StringRes  val AUTH_WELCOME_BACK = R.string.auth_welcome_back
    @StringRes  val AUTH_LOGIN_SUBTITLE = R.string.auth_login_subtitle
    @StringRes  val AUTH_CREATE_ACCOUNT_TITLE = R.string.auth_create_account_title
    @StringRes  val AUTH_SIGNUP_SUBTITLE = R.string.auth_signup_subtitle
    @StringRes  val AUTH_SIGN_IN_WITH_GOOGLE = R.string.auth_sign_in_with_google
    @StringRes  val AUTH_OR = R.string.auth_or
    @StringRes  val AUTH_GOOGLE_SIGN_IN_FAILED = R.string.auth_google_sign_in_failed

    // OTP
    @StringRes  val AUTH_OTP_TITLE = R.string.auth_otp_title
    @StringRes  val AUTH_OTP_HEADLINE = R.string.auth_otp_headline
    @StringRes  val AUTH_OTP_SUBTITLE = R.string.auth_otp_subtitle
    @StringRes  val AUTH_OTP_VERIFY = R.string.auth_otp_verify
    @StringRes  val AUTH_OTP_NO_CODE = R.string.auth_otp_no_code
    @StringRes  val AUTH_OTP_RESEND = R.string.auth_otp_resend
    @StringRes  val AUTH_OTP_RESEND_SUCCESS = R.string.auth_otp_resend_success
    @StringRes  val AUTH_OTP_RESEND_IN = R.string.auth_otp_resend_in
    @StringRes  val AUTH_OTP_SECONDS = R.string.auth_otp_seconds

    // Forgot & Reset Password
    @StringRes  val AUTH_FORGOT_PASSWORD_TITLE = R.string.auth_forgot_password_title
    @StringRes  val AUTH_FORGOT_PASSWORD_HEADLINE = R.string.auth_forgot_password_headline
    @StringRes  val AUTH_FORGOT_PASSWORD_SUBTITLE = R.string.auth_forgot_password_subtitle
    @StringRes  val AUTH_FORGOT_PASSWORD_SEND = R.string.auth_forgot_password_send
    @StringRes  val AUTH_FORGOT_PASSWORD_BACK_TO_LOGIN = R.string.auth_forgot_password_back_to_login

    @StringRes  val AUTH_RESET_PASSWORD_TITLE = R.string.auth_reset_password_title
    @StringRes  val AUTH_RESET_PASSWORD_HEADLINE = R.string.auth_reset_password_headline
    @StringRes  val AUTH_RESET_PASSWORD_SUBTITLE = R.string.auth_reset_password_subtitle
    @StringRes  val AUTH_RESET_PASSWORD_NEW_PASSWORD = R.string.auth_reset_password_new_password
    @StringRes  val AUTH_RESET_PASSWORD_SUBMIT = R.string.auth_reset_password_submit
    @StringRes  val AUTH_RESET_PASSWORD_SUCCESS = R.string.auth_reset_password_success
    @StringRes  val AUTH_RESET_PASSWORD_OTP_REQUIRED = R.string.auth_reset_password_otp_required

    // ==================== ONBOARDING ====================
    @StringRes  val ONBOARDING_PAGE1_TITLE = R.string.onboarding_page1_title
    @StringRes  val ONBOARDING_PAGE1_SUBTITLE = R.string.onboarding_page1_subtitle
    @StringRes  val ONBOARDING_PAGE2_TITLE = R.string.onboarding_page2_title
    @StringRes  val ONBOARDING_PAGE2_SUBTITLE = R.string.onboarding_page2_subtitle
    @StringRes  val ONBOARDING_PAGE3_TITLE = R.string.onboarding_page3_title
    @StringRes  val ONBOARDING_PAGE3_SUBTITLE = R.string.onboarding_page3_subtitle
    @StringRes  val ONBOARDING_PAGE4_TITLE = R.string.onboarding_page4_title
    @StringRes  val ONBOARDING_PAGE4_SUBTITLE = R.string.onboarding_page4_subtitle
    @StringRes  val ONBOARDING_SKIP = R.string.onboarding_skip
    @StringRes  val ONBOARDING_NEXT = R.string.onboarding_next
    @StringRes  val ONBOARDING_START = R.string.onboarding_start

    // ==================== MEDICATION ====================
    @StringRes  val MEDICATION_TITLE = R.string.medication_title
    @StringRes  val MEDICATION_COUNT = R.string.medication_count
    @StringRes  val MEDICATION_FILTER_ALL = R.string.medication_filter_all
    @StringRes  val MEDICATION_FILTER_DAILY = R.string.medication_filter_daily
    @StringRes  val MEDICATION_FILTER_WEEKLY = R.string.medication_filter_weekly
    @StringRes  val MEDICATION_FILTER_AS_NEEDED = R.string.medication_filter_as_needed
    @StringRes  val MEDICATION_DELETE_TITLE = R.string.medication_delete_title
    @StringRes  val MEDICATION_DELETE_CONFIRM = R.string.medication_delete_confirm
    @StringRes  val MEDICATION_DELETE = R.string.medication_delete
    @StringRes  val MEDICATION_CANCEL = R.string.medication_cancel

    // ==================== HOME ====================
    @StringRes  val HOME_HEADER_TITLE = R.string.home_header_title
    @StringRes  val HOME_HEADER_SUBTITLE = R.string.home_header_subtitle
    @StringRes  val HOME_NO_NOTIFICATIONS = R.string.home_no_notifications

    @StringRes  val HOME_SECTIONS_NEARBY = R.string.home_sections_nearby
    @StringRes  val HOME_SECTIONS_ALL_CLINICS = R.string.home_sections_all_clinics
    @StringRes  val HOME_SECTIONS_FEATURED = R.string.home_sections_featured
    @StringRes  val HOME_SECTIONS_NO_MORE = R.string.home_sections_no_more
    @StringRes  val HOME_SECTIONS_EMPTY_TITLE = R.string.home_sections_empty_title
    @StringRes  val HOME_SECTIONS_EMPTY_SUBTITLE = R.string.home_sections_empty_subtitle

    // Last Booking
    @StringRes  val HOME_LAST_BOOKING_BADGE = R.string.home_last_booking_badge
    @StringRes  val HOME_LAST_BOOKING_QUEUE_POSITION = R.string.home_last_booking_queue_position
    @StringRes  val HOME_LAST_BOOKING_YOUR_TURN = R.string.home_last_booking_your_turn
    @StringRes  val HOME_LAST_BOOKING_PEOPLE_AHEAD = R.string.home_last_booking_people_ahead
    @StringRes  val HOME_LAST_BOOKING_NO_BOOKINGS_TITLE = R.string.home_last_booking_no_bookings_title
    @StringRes  val HOME_LAST_BOOKING_NO_BOOKINGS_SUBTITLE = R.string.home_last_booking_no_bookings_subtitle

    // Last Booking Tips
    @StringRes  val HOME_LAST_BOOKING_TIP_PENDING_TITLE = R.string.home_last_booking_tip_pending_title
    @StringRes  val HOME_LAST_BOOKING_TIP_PENDING_BODY = R.string.home_last_booking_tip_pending_body
    @StringRes  val HOME_LAST_BOOKING_TIP_CONFIRMED_TITLE = R.string.home_last_booking_tip_confirmed_title
    @StringRes  val HOME_LAST_BOOKING_TIP_CONFIRMED_BODY = R.string.home_last_booking_tip_confirmed_body
    @StringRes  val HOME_LAST_BOOKING_TIP_ALMOST_TITLE = R.string.home_last_booking_tip_almost_title
    @StringRes  val HOME_LAST_BOOKING_TIP_ALMOST_BODY = R.string.home_last_booking_tip_almost_body
    @StringRes  val HOME_LAST_BOOKING_TIP_YOUR_TURN_TITLE = R.string.home_last_booking_tip_your_turn_title
    @StringRes  val HOME_LAST_BOOKING_TIP_YOUR_TURN_BODY = R.string.home_last_booking_tip_your_turn_body
    @StringRes  val HOME_LAST_BOOKING_TIP_CANCELLED_TITLE = R.string.home_last_booking_tip_cancelled_title
    @StringRes  val HOME_LAST_BOOKING_TIP_CANCELLED_BODY = R.string.home_last_booking_tip_cancelled_body
    @StringRes  val HOME_LAST_BOOKING_TIP_IN_DOCTOR_TITLE = R.string.home_last_booking_tip_in_doctor_title
    @StringRes  val HOME_LAST_BOOKING_TIP_IN_DOCTOR_BODY = R.string.home_last_booking_tip_in_doctor_body
    @StringRes  val HOME_LAST_BOOKING_TIP_COMPLETED_TITLE = R.string.home_last_booking_tip_completed_title
    @StringRes  val HOME_LAST_BOOKING_TIP_COMPLETED_BODY = R.string.home_last_booking_tip_completed_body
    @StringRes  val HOME_LAST_BOOKING_TIP_DISMISS = R.string.home_last_booking_tip_dismiss

    // ==================== FAVORITES ====================
    @StringRes  val FAVORITES_TITLE = R.string.favorites_title
    @StringRes  val FAVORITES_EMPTY_TITLE = R.string.favorites_empty_title
    @StringRes  val FAVORITES_EMPTY_SUBTITLE = R.string.favorites_empty_subtitle
    @StringRes  val FAVORITES_FIND_CLINIC = R.string.favorites_find_clinic
    @StringRes  val FAVORITES_RETRY = R.string.favorites_retry
    @StringRes  val FAVORITES_REMOVE = R.string.favorites_remove

    // ==================== BOOKINGS ====================
    @StringRes  val BOOKINGS_TITLE = R.string.bookings_title
    @StringRes  val BOOKINGS_AUTH_REQUIRED_TITLE = R.string.bookings_auth_required_title
    @StringRes  val BOOKINGS_AUTH_REQUIRED_SUBTITLE = R.string.bookings_auth_required_subtitle
    @StringRes  val BOOKINGS_EMPTY_TITLE = R.string.bookings_empty_title
    @StringRes  val BOOKINGS_EMPTY_SUBTITLE = R.string.bookings_empty_subtitle
    @StringRes  val BOOKINGS_FIND_CLINIC = R.string.bookings_find_clinic
    @StringRes  val BOOKINGS_RETRY = R.string.bookings_retry
    @StringRes  val BOOKINGS_DOCTOR = R.string.bookings_doctor
    @StringRes  val BOOKINGS_DATE = R.string.bookings_date
    @StringRes  val BOOKINGS_TIME = R.string.bookings_time
    @StringRes  val BOOKINGS_TURN_NUMBER = R.string.bookings_turn_number
    @StringRes  val BOOKINGS_REVIEWED = R.string.bookings_reviewed

    @StringRes  val BOOKINGS_WAIT_TURNS_LABEL = R.string.bookings_wait_turns_label
    @StringRes  val BOOKINGS_WAIT_TURNS_YOUR_TURN = R.string.bookings_wait_turns_your_turn
    @StringRes  val BOOKINGS_WAIT_TURNS_COUNT = R.string.bookings_wait_turns_count

    // Booking Detail
    @StringRes  val BOOKING_DETAIL_TITLE = R.string.booking_detail_title
    @StringRes  val BOOKING_DETAIL_APPOINTMENT_INFO = R.string.booking_detail_appointment_info
    @StringRes  val BOOKING_DETAIL_PATIENT_INFO = R.string.booking_detail_patient_info
    @StringRes  val BOOKING_DETAIL_PATIENT_NAME = R.string.booking_detail_patient_name
    @StringRes  val BOOKING_DETAIL_PATIENT_PHONE = R.string.booking_detail_patient_phone
    @StringRes  val BOOKING_DETAIL_SPECIALTY = R.string.booking_detail_specialty
    @StringRes  val BOOKING_DETAIL_NOTES = R.string.booking_detail_notes
    @StringRes  val BOOKING_DETAIL_CANCEL_BOOKING = R.string.booking_detail_cancel_booking
    @StringRes  val BOOKING_DETAIL_CANCEL_CONFIRM_TITLE = R.string.booking_detail_cancel_confirm_title
    @StringRes  val BOOKING_DETAIL_CANCEL_CONFIRM_BODY = R.string.booking_detail_cancel_confirm_body
    @StringRes  val BOOKING_DETAIL_CANCEL_CONFIRM_YES = R.string.booking_detail_cancel_confirm_yes
    @StringRes  val BOOKING_DETAIL_CANCEL_SUCCESS = R.string.booking_detail_cancel_success

    // Booking Status
    @StringRes  val BOOKING_STATUS_CONFIRMED = R.string.booking_status_confirmed
    @StringRes  val BOOKING_STATUS_CANCELLED = R.string.booking_status_cancelled
    @StringRes  val BOOKING_STATUS_PENDING = R.string.booking_status_pending
    @StringRes  val BOOKING_STATUS_IN_DOCTOR = R.string.booking_status_in_doctor
    @StringRes  val BOOKING_STATUS_COMPLETED = R.string.booking_status_completed

    // ==================== REVIEWS ====================
    @StringRes  val REVIEW_FORM_TITLE = R.string.review_form_title
    @StringRes  val REVIEW_FORM_RATING_LABEL = R.string.review_form_rating_label
    @StringRes  val REVIEW_FORM_COMMENT_LABEL = R.string.review_form_comment_label
    @StringRes  val REVIEW_FORM_COMMENT_HINT = R.string.review_form_comment_hint
    @StringRes  val REVIEW_FORM_SUBMIT = R.string.review_form_submit
    @StringRes  val REVIEW_FORM_SUCCESS = R.string.review_form_success

    // ==================== BOOKING FLOW ====================
    @StringRes  val BOOKING_CHOOSE_DATE = R.string.booking_choose_date
    @StringRes  val BOOKING_CHOOSE_TIME = R.string.booking_choose_time
    @StringRes  val BOOKING_NO_SLOTS = R.string.booking_no_slots
    @StringRes  val BOOKING_CONTINUE = R.string.booking_continue
    @StringRes  val BOOKING_CONFIRM_TITLE = R.string.booking_confirm_title
    @StringRes  val BOOKING_CONFIRM_SUBTITLE = R.string.booking_confirm_subtitle
    @StringRes  val BOOKING_CONFIRM_BUTTON = R.string.booking_confirm_button
    @StringRes  val BOOKING_CONFIRMED_TITLE = R.string.booking_confirmed_title
    @StringRes  val BOOKING_CONFIRMED_BODY = R.string.booking_confirmed_body
    @StringRes  val BOOKING_CONFIRMED_DONE = R.string.booking_confirmed_done
    @StringRes  val BOOKING_PAYMENT_NOTE = R.string.booking_payment_note
    @StringRes  val BOOKING_YOUR_INFO = R.string.booking_your_info
    @StringRes  val BOOKING_FULL_NAME = R.string.booking_full_name
    @StringRes  val BOOKING_FULL_NAME_HINT = R.string.booking_full_name_hint
    @StringRes  val BOOKING_PHONE = R.string.booking_phone
    @StringRes  val BOOKING_PHONE_HINT = R.string.booking_phone_hint
    @StringRes  val BOOKING_NOTES_OPTIONAL = R.string.booking_notes_optional
    @StringRes  val BOOKING_NOTES_HINT = R.string.booking_notes_hint
    @StringRes  val BOOKING_REVIEWED = R.string.booking_reviewed
    @StringRes  val BOOKING_MISSING_INFO = R.string.booking_missing_info

    // Booking Summary
    @StringRes  val BOOKING_SUMMARY_CLINIC = R.string.booking_summary_clinic
    @StringRes  val BOOKING_SUMMARY_DOCTOR = R.string.booking_summary_doctor
    @StringRes  val BOOKING_SUMMARY_PATIENT = R.string.booking_summary_patient
    @StringRes  val BOOKING_SUMMARY_PHONE = R.string.booking_summary_phone
    @StringRes  val BOOKING_SUMMARY_DATE = R.string.booking_summary_date
    @StringRes  val BOOKING_SUMMARY_TIME = R.string.booking_summary_time
    @StringRes  val BOOKING_SUMMARY_NOTES = R.string.booking_summary_notes

    @StringRes  val BOOKING_BOTTOM_BAR_FEE_LABEL = R.string.booking_bottom_bar_fee_label
    @StringRes  val BOOKING_BOTTOM_BAR_BOOK_NOW = R.string.booking_bottom_bar_book_now

    @StringRes  val BOOKING_STEPPER_DATE_TIME = R.string.booking_stepper_date_time
    @StringRes  val BOOKING_STEPPER_YOUR_INFO = R.string.booking_stepper_your_info
    @StringRes  val BOOKING_STEPPER_CONFIRM = R.string.booking_stepper_confirm

    // ==================== CLINIC ====================
    @StringRes  val CLINIC_CHOOSE_DATE = R.string.clinic_choose_date
    @StringRes  val CLINIC_STATUS_OPEN = R.string.clinic_status_open
    @StringRes  val CLINIC_STATUS_CLOSED = R.string.clinic_status_closed
    @StringRes  val CLINIC_AVAILABLE_DOCTORS = R.string.clinic_available_doctors
    @StringRes  val CLINIC_NO_DOCTORS = R.string.clinic_no_doctors
    @StringRes  val CLINIC_NO_SLOTS_TODAY = R.string.clinic_no_slots_today
    @StringRes  val CLINIC_CHOOSE_TIME = R.string.clinic_choose_time
    @StringRes  val CLINIC_REMAINING = R.string.clinic_remaining
    @StringRes  val CLINIC_SELECTED_SUMMARY_CLEAR = R.string.clinic_selected_summary_clear
    @StringRes  val CLINIC_BOOK_NOW = R.string.clinic_book_now
    @StringRes  val CLINIC_CURRENCY = R.string.clinic_currency
    @StringRes  val CLINIC_YEARS_EXP = R.string.clinic_years_exp

    @StringRes  val CLINIC_TAB_OVERVIEW = R.string.clinic_tab_overview
    @StringRes  val CLINIC_TAB_BOOKING = R.string.clinic_tab_booking
    @StringRes  val CLINIC_TAB_REVIEWS = R.string.clinic_tab_reviews

    // Clinic Info, Services & Reviews
    @StringRes  val CLINIC_INFO_ABOUT = R.string.clinic_info_about
    @StringRes  val CLINIC_INFO_OPENING_HOURS = R.string.clinic_info_opening_hours
    @StringRes  val CLINIC_INFO_LOCATION = R.string.clinic_info_location
    @StringRes  val CLINIC_INFO_CONTACT = R.string.clinic_info_contact
    @StringRes  val CLINIC_INFO_CALL = R.string.clinic_info_call
    @StringRes  val CLINIC_INFO_WHATSAPP = R.string.clinic_info_whatsapp
    @StringRes  val CLINIC_INFO_OPEN_MAPS = R.string.clinic_info_open_maps
    @StringRes  val CLINIC_INFO_SHOW_MORE = R.string.clinic_info_show_more
    @StringRes  val CLINIC_INFO_SHOW_LESS = R.string.clinic_info_show_less

    @StringRes  val CLINIC_SERVICES_TITLE = R.string.clinic_services_title
    @StringRes  val CLINIC_SERVICES_AVAILABLE = R.string.clinic_services_available
    @StringRes  val CLINIC_SERVICES_FACILITIES = R.string.clinic_services_facilities
    @StringRes  val CLINIC_SERVICES_INSURANCE = R.string.clinic_services_insurance
    @StringRes  val CLINIC_SERVICES_EMPTY_TITLE = R.string.clinic_services_empty_title
    @StringRes  val CLINIC_SERVICES_EMPTY_SUBTITLE = R.string.clinic_services_empty_subtitle

    @StringRes  val CLINIC_REVIEWS_TITLE = R.string.clinic_reviews_title
    @StringRes  val CLINIC_REVIEWS_PATIENTS_REVIEWS = R.string.clinic_reviews_patients_reviews
    @StringRes  val CLINIC_REVIEWS_EMPTY = R.string.clinic_reviews_empty
    @StringRes  val CLINIC_REVIEWS_WITH_DOCTOR = R.string.clinic_reviews_with_doctor
    @StringRes  val CLINIC_REVIEWS_TOTAL = R.string.clinic_reviews_total

    // ==================== SEARCH ====================
    @StringRes  val SEARCH_HINT = R.string.search_hint
    @StringRes  val SEARCH_INITIAL_TITLE = R.string.search_initial_title
    @StringRes  val SEARCH_INITIAL_HINT = R.string.search_initial_hint
    @StringRes  val SEARCH_EMPTY_TITLE = R.string.search_empty_title
    @StringRes  val SEARCH_NO_RESULTS = R.string.search_no_results

    @StringRes  val SEARCH_FILTER_LABEL = R.string.search_filter_label
    @StringRes  val SEARCH_FILTER_TITLE = R.string.search_filter_title
    @StringRes  val SEARCH_FILTER_CLEAR_ALL = R.string.search_filter_clear_all
    @StringRes  val SEARCH_FILTER_APPLY = R.string.search_filter_apply
    @StringRes  val SEARCH_FILTER_ALL = R.string.search_filter_all
    @StringRes  val SEARCH_FILTER_AVAILABILITY = R.string.search_filter_availability
    @StringRes  val SEARCH_FILTER_OPEN = R.string.search_filter_open
    @StringRes  val SEARCH_FILTER_CLOSED = R.string.search_filter_closed
    @StringRes  val SEARCH_FILTER_MIN_RATING = R.string.search_filter_min_rating

    // ==================== SETTINGS ====================
    @StringRes  val SETTINGS_GUEST_TITLE = R.string.settings_guest_title
    @StringRes  val SETTINGS_GUEST_SUBTITLE = R.string.settings_guest_subtitle

    @StringRes  val SETTINGS_SECTION_ACCOUNT = R.string.settings_section_account
    @StringRes  val SETTINGS_SECTION_PREFERENCES = R.string.settings_section_preferences
    @StringRes  val SETTINGS_SECTION_SUPPORT = R.string.settings_section_support
    @StringRes  val SETTINGS_SECTION_LEGAL = R.string.settings_section_legal

    @StringRes  val SETTINGS_ACCOUNT_PROFILE = R.string.settings_account_profile
    @StringRes  val SETTINGS_ACCOUNT_PROFILE_SUB = R.string.settings_account_profile_sub
    @StringRes  val SETTINGS_ACCOUNT_CHANGE_PASSWORD = R.string.settings_account_change_password
    @StringRes  val SETTINGS_ACCOUNT_CHANGE_PASSWORD_SUB = R.string.settings_account_change_password_sub
    @StringRes  val SETTINGS_ACCOUNT_BOOKINGS = R.string.settings_account_bookings
    @StringRes  val SETTINGS_ACCOUNT_BOOKINGS_SUB = R.string.settings_account_bookings_sub

    @StringRes  val SETTINGS_PREFERENCES_NOTIFICATIONS = R.string.settings_preferences_notifications
    @StringRes  val SETTINGS_PREFERENCES_NOTIFICATIONS_SUB = R.string.settings_preferences_notifications_sub
    @StringRes  val SETTINGS_PREFERENCES_LANGUAGE = R.string.settings_preferences_language
    @StringRes  val SETTINGS_PREFERENCES_ARABIC = R.string.settings_preferences_arabic
    @StringRes  val SETTINGS_PREFERENCES_ENGLISH = R.string.settings_preferences_english
    @StringRes  val SETTINGS_PREFERENCES_CHOOSE_LANGUAGE = R.string.settings_preferences_choose_language
    @StringRes  val SETTINGS_PREFERENCES_DARK_MODE = R.string.settings_preferences_dark_mode
    @StringRes  val SETTINGS_PREFERENCES_DARK_ON = R.string.settings_preferences_dark_on
    @StringRes  val SETTINGS_PREFERENCES_DARK_OFF = R.string.settings_preferences_dark_off

    @StringRes  val SETTINGS_SUPPORT_HELP = R.string.settings_support_help
    @StringRes  val SETTINGS_SUPPORT_HELP_SUB = R.string.settings_support_help_sub
    @StringRes  val SETTINGS_SUPPORT_CONTACT = R.string.settings_support_contact
    @StringRes  val SETTINGS_SUPPORT_CONTACT_SUB = R.string.settings_support_contact_sub
    @StringRes  val SETTINGS_SUPPORT_RATE = R.string.settings_support_rate
    @StringRes  val SETTINGS_SUPPORT_RATE_SUB = R.string.settings_support_rate_sub

    @StringRes  val SETTINGS_LEGAL_TERMS = R.string.settings_legal_terms
    @StringRes  val SETTINGS_LEGAL_TERMS_SUB = R.string.settings_legal_terms_sub
    @StringRes  val SETTINGS_LEGAL_PRIVACY = R.string.settings_legal_privacy
    @StringRes  val SETTINGS_LEGAL_PRIVACY_SUB = R.string.settings_legal_privacy_sub
    @StringRes  val SETTINGS_LEGAL_ABOUT = R.string.settings_legal_about
    @StringRes  val SETTINGS_LEGAL_VERSION = R.string.settings_legal_version
    @StringRes  val SETTINGS_LEGAL_DELETE_ACCOUNT = R.string.settings_legal_delete_account
    @StringRes  val SETTINGS_LEGAL_DELETE_ACCOUNT_SUB = R.string.settings_legal_delete_account_sub
    @StringRes  val SETTINGS_LEGAL_DELETE_CONFIRM = R.string.settings_legal_delete_confirm
    @StringRes  val SETTINGS_LEGAL_DELETE_YES = R.string.settings_legal_delete_yes

    @StringRes  val SETTINGS_LOGOUT = R.string.settings_logout
    @StringRes  val SETTINGS_LOGOUT_CONFIRM = R.string.settings_logout_confirm

    // ==================== VALIDATION & COMMON ====================
    @StringRes  val VALIDATION_EMAIL_REQUIRED = R.string.validation_email_required
    @StringRes  val VALIDATION_EMAIL_INVALID = R.string.validation_email_invalid
    @StringRes  val VALIDATION_PASSWORD_REQUIRED = R.string.validation_password_required
    @StringRes  val VALIDATION_PASSWORD_MIN_LENGTH = R.string.validation_password_min_length
    @StringRes  val VALIDATION_PASSWORD_MISMATCH = R.string.validation_password_mismatch
    @StringRes  val VALIDATION_NAME_REQUIRED = R.string.validation_name_required
    @StringRes  val VALIDATION_NAME_TOO_SHORT = R.string.validation_name_too_short
    @StringRes  val VALIDATION_PHONE_REQUIRED = R.string.validation_phone_required

    @StringRes  val COMMON_RETRY = R.string.common_retry
    @StringRes  val COMMON_TRY_AGAIN = R.string.common_try_again
    @StringRes  val COMMON_CANCEL = R.string.common_cancel
    @StringRes  val COMMON_OK = R.string.common_ok
    @StringRes  val COMMON_NO = R.string.common_no
    @StringRes  val COMMON_LOGIN = R.string.common_login

    // ==================== PROFILE ====================
    @StringRes  val PROFILE_APP_BAR_TITLE = R.string.profile_app_bar_title
    @StringRes  val PROFILE_COMPLETION_TITLE = R.string.profile_completion_title
    @StringRes  val PROFILE_COMPLETION_DONE = R.string.profile_completion_done
    @StringRes  val PROFILE_COMPLETION_OF_5 = R.string.profile_completion_of_5
    @StringRes  val PROFILE_COMPLETION_HINT0 = R.string.profile_completion_hint0
    @StringRes  val PROFILE_COMPLETION_HINT1 = R.string.profile_completion_hint1
    @StringRes  val PROFILE_COMPLETION_HINT2 = R.string.profile_completion_hint2
    @StringRes  val PROFILE_COMPLETION_HINT3 = R.string.profile_completion_hint3
    @StringRes  val PROFILE_COMPLETION_HINT4 = R.string.profile_completion_hint4

    @StringRes  val PROFILE_SAVE = R.string.profile_save
    @StringRes  val PROFILE_SAVE_SUCCESS = R.string.profile_save_success
    @StringRes  val PROFILE_NO_CHANGES = R.string.profile_no_changes

    // ==================== LOCATION ====================
    @StringRes  val LOCATION_ACCESS_NEEDED = R.string.location_access_needed
    @StringRes  val LOCATION_ENABLE_MANUALLY = R.string.location_enable_manually
    @StringRes  val LOCATION_WHY_WE_USE = R.string.location_why_we_use
    @StringRes  val LOCATION_OPEN_SETTINGS = R.string.location_open_settings
    @StringRes  val LOCATION_ENTER_MANUALLY = R.string.location_enter_manually
    @StringRes  val LOCATION_DISABLED_TITLE = R.string.location_disabled_title
    @StringRes  val LOCATION_ENABLE_PROMPT = R.string.location_enable_prompt

    @StringRes  val LOCATION_STEPS_TITLE_ANDROID = R.string.location_steps_title_android
    @StringRes  val LOCATION_STEPS_TITLE_IOS = R.string.location_steps_title_ios

    @StringRes  val LOCATION_ANDROID_STEP1 = R.string.location_android_step1
    @StringRes  val LOCATION_ANDROID_STEP2 = R.string.location_android_step2
    @StringRes  val LOCATION_ANDROID_STEP3 = R.string.location_android_step3
    @StringRes  val LOCATION_ANDROID_STEP4 = R.string.location_android_step4
    @StringRes  val LOCATION_ANDROID_STEP5 = R.string.location_android_step5

    @StringRes  val LOCATION_IOS_STEP1 = R.string.location_ios_step1
    @StringRes  val LOCATION_IOS_STEP2 = R.string.location_ios_step2
    @StringRes  val LOCATION_IOS_STEP3 = R.string.location_ios_step3
    @StringRes  val LOCATION_IOS_STEP4 = R.string.location_ios_step4

    // ==================== ERRORS ====================
    @StringRes  val ERROR_NETWORK_TITLE = R.string.error_network_title
    @StringRes  val ERROR_NETWORK_SUBTITLE = R.string.error_network_subtitle
    @StringRes  val ERROR_NETWORK_ACTION = R.string.error_network_action
    // Add more error strings as needed...
}