package org.gvozdev.tetrokot.ui.score

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

class ScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val score: Int = intent.getIntExtra("score", 0)
        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScoreView(score)
                }
            }
        }
    }
}