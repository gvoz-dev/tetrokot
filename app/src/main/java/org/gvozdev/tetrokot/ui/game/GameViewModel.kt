package org.gvozdev.tetrokot.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.gvozdev.tetrokot.game.GameState
import org.gvozdev.tetrokot.game.Status.*

class GameViewModel : ViewModel() {
    // https://elizarov.medium.com/shared-flows-broadcast-channels-899b675e805c
    private val _game = MutableStateFlow(GameState(10, 20))
    val game = _game.asStateFlow()

    fun update(event: Event) = reduce(game.value, event)

    private fun emit(gameState: GameState) {
        _game.value = gameState
    }

    private fun reduce(gameState: GameState, event: Event) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                if (gameState.status == GAME_OVER) return@withContext
                when (event) {
                    MoveLeft -> gameState.status = LEFT
                    MoveRight -> gameState.status = RIGHT
                    Rotate -> gameState.status = ROTATE
                    else -> {}
                }
                emit(newState(gameState))
            }
        }
    }

    private fun newState(gameState: GameState) = when (gameState.status) {
        NEXT -> gameState.copy(status = gameState.showNext())
        GAME_OVER -> gameState.copy(ticks = gameState.ticks + 1)
        LEFT -> gameState.copy(status = gameState.moveLeft())
        RIGHT -> gameState.copy(status = gameState.moveRight())
        ROTATE -> gameState.copy(status = gameState.rotate())
        else -> gameState.copy(ticks = gameState.ticks + 1, status = gameState.moveDown())
    }
}

sealed interface Event
object GameTick : Event
object MoveLeft : Event
object MoveRight : Event
object Rotate : Event