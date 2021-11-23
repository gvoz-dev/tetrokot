package org.gvozdev.tetrokot.game

class GameState(width: Int, height: Int) {
    val field = Field(width, height)
    var current = Tetromino.next
    var next = Tetromino.next
    var x = startX()
    var y = startY()
    var state = State.NEXT
    var score = 0

    enum class State {
        NEXT, DOWN, LEFT, RIGHT, ROTATE, GAME_OVER
    }

    private fun startX() = field.w / 2 - current.w / 2
    private fun startY() = if (current.type === Tetromino.Type.I) field.wall - 1 else field.wall

    private fun initTetromino() {
        current = next
        next = Tetromino.next
        x = startX()
        y = startY()
    }

    fun showNext() {
        initTetromino()
        state = if (isValidAction(x, y, current, field)) {
            field.moveTetromino(current, x, y)
            State.DOWN
        } else {
            State.GAME_OVER
        }
    }

    fun moveDown() {
        field.clearTetromino(current, x, y)
        if (isValidAction(x, y + 1, current, field)) {
            field.moveTetromino(current, x, ++y)
        } else {
            field.moveTetromino(current, x, y)
            state = State.NEXT
        }
    }

    private fun moveLeft() {
        field.clearTetromino(current, x, y)
        if (isValidAction(x - 1, y, current, field)) {
            field.moveTetromino(current, --x, y)
        } else {
            field.moveTetromino(current, x, y)
        }
        state = State.DOWN
    }

    private fun moveRight() {
        field.clearTetromino(current, x, y)
        if (isValidAction(x + 1, y, current, field)) {
            field.moveTetromino(current, ++x, y)
        } else {
            field.moveTetromino(current, x, y)
        }
        state = State.DOWN
    }

    private fun rotate() {
        field.clearTetromino(current, x, y)
        current.rotateRight()
        if (!isValidAction(x, y, current, field)) {
            current.rotateLeft()
        }
        field.moveTetromino(current, x, y)
    }
}