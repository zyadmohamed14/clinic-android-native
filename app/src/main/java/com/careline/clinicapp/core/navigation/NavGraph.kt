package com.careline.clinicapp.core.navigation



import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.careline.clinicapp.R
import com.careline.clinicapp.core.ants.AppStrings
import com.careline.clinicapp.core.api.interceptor.AuthEvent
import com.careline.clinicapp.core.api.interceptor.AuthEventBus

import com.careline.clinicapp.core.constants.DrawableResources
import com.careline.clinicapp.core.theme.extraColors
import com.careline.clinicapp.core.ui.components.AppButton
import com.careline.clinicapp.core.ui.components.AppButtonType
import com.careline.clinicapp.core.ui.components.EmailTextField
import com.careline.clinicapp.core.ui.components.PasswordTextField
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Temporary theme preview — replaced with real navigation in Step 5.
 */
@Composable
fun NavGraph(
    authEventBus: AuthEventBus,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.heart_pop)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )


    // Listen for 401 events from the network layer
    LaunchedEffect(Unit) {
        authEventBus.events.collect { event ->
            when (event) {
                is AuthEvent.Unauthorized -> {
                    // TODO: navigate to login in Step 6 when
                    // NavController is set up
                }
            }
        }
    }


}


