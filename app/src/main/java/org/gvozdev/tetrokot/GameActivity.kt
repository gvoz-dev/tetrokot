package org.gvozdev.tetrokot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.gvozdev.tetrokot.ui.GameView
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

@Preview(showBackground = true)
@Composable
fun DefaultGamePreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            GameView()
        }
    }
}