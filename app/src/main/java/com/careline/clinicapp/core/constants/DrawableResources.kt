package com.careline.clinicapp.core.constants



import com.careline.clinicapp.R

/**
 * Single source of truth for all drawable and raw resource IDs.
 * Flutter equivalent: Assets class in core/utils/assets.dart
 */
object DrawableResources {
     val appLogo = R.drawable.logo
    // ── Navigation bar icons ──────────────────────────────────────────────────
    val icHome = R.drawable.home
    val icPills = R.drawable.pills_icon
    val icCalendarNav = R.drawable.calendar_nav_bar
    val icSettings = R.drawable.settings_icon
    val icChats = R.drawable.chats_icon

    // ── General icons ─────────────────────────────────────────────────────────
    val icLocation = R.drawable.location
    val icDoctor = R.drawable.doctor_icon
    val icSearch = R.drawable.search_icon
    val icSearchNormal = R.drawable.search_normal
    val icNotification = R.drawable.notification_bing
    val icArrowLeft = R.drawable.arrow_left
    val icArrowDown = R.drawable.arrow_down
    val icCalendar = R.drawable.calendar
    val icPerson = R.drawable.person_icon
    val icVerified = R.drawable.verified
    val icFavouriteHeart = R.drawable.favourite_heart2
    val icGoogleIcon = R.drawable.google_icon
    val icClinicalBooking = R.drawable.clinical_booking
  //  val icPlaceholder = R.drawable.ic_placeholder

    // ── Images ────────────────────────────────────────────────────────────────
    val appBarBg = R.drawable.appbar_bg  // add this PNG to drawable/

    // ── Lottie animations (res/raw/) ──────────────────────────────────────────
    // These MUST be lowercase — rename the files first
    val lottieHeartPop = R.raw.heart_pop
    val lottieErrorState = R.raw.error_state
    val lottieNoConnection = R.raw.no_connection
    val lottieArriveJustInTime = R.raw.arrive_just_in_time
    val lottieDiscoverTopClinics = R.raw.discover_top_clinics
    val lottieWaitingInQueue = R.raw.waiting_in_a_queue
}