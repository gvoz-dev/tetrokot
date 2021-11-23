package org.gvozdev.tetrokot.game

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val gameState = mutableStateOf(GameState(10, 20))

    init {
        gameState.value.showNext()
    }
}