package org.gvozdev.tetrokot.ui.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GameView()
                }
            }
        }
    }
}