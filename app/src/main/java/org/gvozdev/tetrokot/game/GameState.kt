package org.gvozdev.tetrokot.game

class GameState(width: Int, height: Int) {
    val field = Field(width, height)
    var current = Tetromino.next
    var x = current.initialHorizontalOffset(field.w)
    var y = current.initialVerticalOffset(field.border)
    var next = Tetromino.next
    var state = State.NEXT_TETROMINO
    var score = 0

    enum class State {
        NEXT_TETROMINO, MOVING_DOWN, MOVING_LEFT, MOVING_RIGHT, ROTATE, GAME_OVER
    }

    private fun initTetromino() {
        current = next
        x = current.initialHorizontalOffset(field.w)
        y = current.initialVerticalOffset(field.border)
        next = Tetromino.next
        score += field.removeFilledRows()
    }

    fun showNext() {
        initTetromino()
        state = if (field.isValidPosition(current, x, y)) {
            field.moveTetromino(current, x, y)
            State.MOVING_DOWN
        } else {
            State.GAME_OVER
        }
    }

    fun moveDown() {
        field.clearTetromino(current, x, y)
        if (field.isValidPosition(current, x, y + 1)) {
            field.moveTetromino(current, x, ++y)
        } else {
            field.moveTetromino(current, x, y)
            state = State.NEXT_TETROMINO
        }
    }

    fun moveLeft() {
        field.clearTetromino(current, x, y)
        if (field.isValidPosition(current, x - 1, y)) {
            field.moveTetromino(current, --x, y)
        } else {
            field.moveTetromino(current, x, y)
        }
        state = State.MOVING_DOWN
    }

    fun moveRight() {
        field.clearTetromino(current, x, y)
        if (field.isValidPosition(current, x + 1, y)) {
            field.moveTetromino(current, ++x, y)
        } else {
            field.moveTetromino(current, x, y)
        }
        state = State.MOVING_DOWN
    }

    fun rotate() {
        field.clearTetromino(current, x, y)
        current.rotateRight()
        if (!field.isValidPosition(current, x, y)) {
            current.rotateLeft()
        }
        field.moveTetromino(current, x, y)
    }
}