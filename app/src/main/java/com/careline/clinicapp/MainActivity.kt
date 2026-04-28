package com.careline.clinicapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.careline.clinicapp.core.api.interceptor.AuthEventBus
import com.careline.clinicapp.core.theme.CarLineTheme
import com.careline.clinicapp.core.ui.components.DevToolsOverlay
import com.careline.clinicapp.feature.auth.presentation.viewmodel.LoginViewModel
import com.careline.clinicapp.feature.auth.presentation.screen.LoginScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   @Inject
 lateinit  var authEventBus: AuthEventBus ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
      //   authEventBus = AuthEventBus()
      //  val loginViewModel = LoginViewModel()
        setContent {
            var isDark by remember { mutableStateOf(false) }
            var isArabic by remember { mutableStateOf(true) }
            val viewModel: LoginViewModel = hiltViewModel()
            CarLineTheme(darkTheme = isDark) {

                ProvideLocale(isArabic = isArabic) {

                    DevToolsOverlay(
                        isDark = isDark,
                        isArabic = isArabic,

                        onToggleTheme = {
                            isDark = !isDark
                        },

                        onToggleLanguage = {
                            isArabic = !isArabic
                        }
                    ) {
                      //  NavGraph(authEventBus)

                        LoginScreen(viewModel)
                    }
                }
            }
        }
    }
}


@SuppressLint("LocalContextConfigurationRead")
@Composable
fun ProvideLocale(
    isArabic: Boolean,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val locale = if (isArabic) Locale("ar") else Locale("en")

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    val localizedContext = context.createConfigurationContext(config)

    androidx.compose.runtime.CompositionLocalProvider(
        LocalContext provides localizedContext
    ) {
        content()
    }
}