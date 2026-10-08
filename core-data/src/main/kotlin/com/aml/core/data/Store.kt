package com.aml.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

val Context.amlDataStore: DataStore<Preferences> by preferencesDataStore(name = "aml_prefs")

object PreferencesKeys {
    val theme = stringPreferencesKey("theme")
    val defaultModelKey = stringPreferencesKey("default_model_key")
    val onboardingDone = stringPreferencesKey("onboarding_done")
}

class AmlPreferences(private val context: Context) {
    val theme: Flow<String> = context.amlDataStore.data.map { prefs ->
        prefs[PreferencesKeys.theme] ?: "SYSTEM"
    }

    suspend fun setTheme(value: String) {
        context.amlDataStore.edit { it[PreferencesKeys.theme] = value }
    }

    suspend fun markOnboardingDone() {
        context.amlDataStore.edit { it[PreferencesKeys.onboardingDone] = "true" }
    }
}

class SecretStore(private val context: Context) {
    fun put(alias: String, secret: String) {
        // Keystore-backed storage is planned for M1; this minimal placeholder keeps app launch stable.
        context.getSharedPreferences("secure_store", Context.MODE_PRIVATE)
            .edit()
            .putString(alias, secret)
            .apply()
    }

    fun get(alias: String): String? = context.getSharedPreferences("secure_store", Context.MODE_PRIVATE)
        .getString(alias, null)

    fun delete(alias: String) {
        context.getSharedPreferences("secure_store", Context.MODE_PRIVATE)
            .edit()
            .remove(alias)
            .apply()
    }
}

object IdGenerator {
    fun newId(): String = UUID.randomUUID().toString()
}
