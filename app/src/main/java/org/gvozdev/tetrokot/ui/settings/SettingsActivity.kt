package org.gvozdev.tetrokot.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tetrokot_settings")
//val SIZE = stringPreferencesKey("field_size")
//val DIFFICULTY = intPreferencesKey("game_difficulty")

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SettingsView()
                }
            }
        }
    }
}