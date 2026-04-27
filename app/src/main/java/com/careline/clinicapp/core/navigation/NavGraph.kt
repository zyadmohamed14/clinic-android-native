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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "CareLine",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Theme is working ✅",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Success color check",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.extraColors.successFill,
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(48.dp),
        )
        Image(
            painter = painterResource(DrawableResources.appLogo),
            contentDescription = null,
            //modifier = Modifier.height(40),
            contentScale = ContentScale.Crop
        )
    }
}


// Just so you can see how clean the call sites are:

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(viewModel: LoginViewModel ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val focusManager = LocalFocusManager.current
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)  // ← correct
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CareLine",
                style = MaterialTheme.typography.displayLarge,
                // color = MaterialTheme.colorScheme.primary
            )
            EmailTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChanged,
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            PasswordTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChanged,
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError,
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.onLoginClicked() }
                )
            )
            AppButton(
                textId = AppStrings.AUTH_LOGIN,
                onClick = { viewModel.onLoginClicked() },
                isLoading = uiState.isLoading,
                enabled = true,
            )

// Outlined
            AppButton(
                textId =AppStrings.AUTH_CONTINUE_AS_GUEST,
                onClick = { },
                type = AppButtonType.Outlined,
                leadingIcon = Icons.Outlined.Person,
            )
        }
    }

}

class LoginViewModel
constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                emailError = null // clear error on typing
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onLoginClicked() {
        val currentState = _uiState.value

        val emailError = validateEmail(currentState.email)
        val passwordError = validatePassword(currentState.password)

        if (emailError != null || passwordError != null) {
            _uiState.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        login(currentState.email, currentState.password)
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // simulate API call
            delay(1500)

            _uiState.update { it.copy(isLoading = false) }

            // هنا تحط navigation أو success state
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                "Invalid email format"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
)