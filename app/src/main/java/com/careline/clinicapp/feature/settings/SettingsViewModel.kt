package com.careline.clinicapp.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.careline.clinicapp.core.datastore.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
data class RecreateEvent(val newLanguage: String)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: AppDataStore,
) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = dataStore.isDarkModeFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val language: StateFlow<String> = dataStore.getLanguageFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "ar",
        )

    private val _recreateEvent = MutableSharedFlow<RecreateEvent>(extraBufferCapacity = 1)
    val recreateEvent: SharedFlow<RecreateEvent> = _recreateEvent.asSharedFlow()

    fun toggleTheme() {
        viewModelScope.launch {
            dataStore.setDarkMode(!isDarkMode.value)
        }
    }

    fun setLanguage(code: String) {
        viewModelScope.launch {
            // 1. Persist to DataStore (source of truth for the rest of the app)
            dataStore.setLanguage(code)
            // 2. Signal MainActivity to sync SharedPreferences then recreate
            _recreateEvent.emit(RecreateEvent(newLanguage = code))
        }
    }
}