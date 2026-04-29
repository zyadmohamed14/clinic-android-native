package com.careline.clinicapp.app

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.core.locale.LocaleManager
import com.careline.clinicapp.core.navigation.AppNavGraph
import com.careline.clinicapp.core.navigation.StartDestinationResolver
import com.careline.clinicapp.core.theme.CarLineTheme
import com.careline.clinicapp.core.ui.components.DevToolsOverlay
import com.careline.clinicapp.feature.auth.presentation.screen.AuthScreen
import com.careline.clinicapp.feature.auth.presentation.viewmodel.LoginViewModel
import com.careline.clinicapp.feature.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authEventBus: AuthEventBus

    @Inject
    lateinit var startDestinationResolver: StartDestinationResolver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Resolve start destination once, synchronously at startup.
        // This is safe here because it's a fast local DataStore read,
        // not a network call. We do it before setContent so the NavHost
        // receives the correct startDestination on first composition.
        val startDestination = runBlocking { startDestinationResolver.resolve() }

        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()

            val isDark by settingsViewModel.isDarkMode
                .collectAsStateWithLifecycle()
            val language by settingsViewModel.language
                .collectAsStateWithLifecycle()

            val navController = rememberNavController()

            CarLineTheme(darkTheme = isDark) {
                DevToolsOverlay(
                    isDark = isDark,
                    isArabic = language == "ar",
                    onToggleTheme = { settingsViewModel.toggleTheme() },
                    onToggleLanguage = {
                        val next = if (language == "ar") "en" else "ar"
                        settingsViewModel.setLanguage(next)
                    },
                ) {
                    ProvideLocale(languageCode = language) {
                        AppNavGraph(
                            navController = navController,
                            authEventBus = authEventBus,
                            startDestination = startDestination,
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("LocalContextConfigurationRead")
@Composable
fun ProvideLocale(
    languageCode: String,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val localizedContext = remember(languageCode) {
        LocaleManager.applyLocale(context, languageCode)
    }

    CompositionLocalProvider(
        LocalContext provides localizedContext,
        content = content,
    )
}