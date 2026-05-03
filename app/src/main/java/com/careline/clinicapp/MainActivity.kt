package com.careline.clinicapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.core.datastore.AppStorage
import com.careline.clinicapp.core.locale.LocaleManager
import com.careline.clinicapp.core.navigation.AppNavGraph
import com.careline.clinicapp.core.navigation.StartDestinationResolver
import com.careline.clinicapp.core.theme.CarLineTheme
import com.careline.clinicapp.core.ui.components.DevToolsOverlay
import com.careline.clinicapp.feature.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authEventBus: AuthEventBus

    @Inject
    lateinit var startDestinationResolver: StartDestinationResolver
   // @Inject lateinit var appDataStore : AppDataStore
   override fun attachBaseContext(newBase: Context) {
       // Single static call — no Hilt needed here
       val language = AppStorage.getLanguageEarly(newBase)
       super.attachBaseContext(LocaleManager.applyLocale(newBase, language))
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val startDestination = runBlocking { startDestinationResolver.resolve() }

        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()

            val isDark by settingsViewModel.isDarkMode
                .collectAsStateWithLifecycle()
            val language by settingsViewModel.language
                .collectAsStateWithLifecycle()

            val navController = rememberNavController()


            val context =  LocalContext.current
            val localizedContext = remember(language) {
                LocaleManager.applyLocale(context, language)
            }
            LaunchedEffect(Unit) {
                settingsViewModel.recreateEvent.collect { event ->
                    // SharedPreferences is already updated inside ViewModel
                    // Just recreate — attachBaseContext will pick up the new value
                    recreate()
                }
            }

            CarLineTheme(darkTheme = isDark) {

               // this method localized not work
                AppNavGraph(
                    navController = navController,
                    authEventBus = authEventBus,
                    startDestination = startDestination,
                )


                DevToolsOverlay(
                    isDark = isDark,
                    isArabic = language == "ar",
                    onToggleTheme = { settingsViewModel.toggleTheme() },
                    onToggleLanguage = {
                        val next = if (language == "ar") "en" else "ar"
                        settingsViewModel.setLanguage(next)
                    },
                ) {

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

