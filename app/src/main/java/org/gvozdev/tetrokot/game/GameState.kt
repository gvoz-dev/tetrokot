package org.gvozdev.tetrokot.game

data class GameState(
    val width: Int,
    val height: Int,
    val field: Field = Field(width, height),
    var status: Status = Status.NEXT,
    var score: Int = 0,
    var ticks: Int = 0,
    var nextTetro: Tetromino = Tetromino.next,
    var currTetro: Tetromino = nextTetro,
    private var x: Int = 0,
    private var y: Int = 0
) {
    private fun initTetromino() {
        currTetro = nextTetro
        x = currTetro.initialHorizontalOffset(field.w)
        y = currTetro.initialVerticalOffset(field.border)
        nextTetro = Tetromino.next
        score += field.removeFilledRows()
    }

    fun showNext(): Status {
        initTetromino()
        return if (field.isValidPosition(currTetro, x, y)) {
            field.moveTetromino(currTetro, x, y)
            Status.DOWN
        } else {
            Status.GAME_OVER
        }
    }

    fun moveDown(): Status {
        field.clearTetromino(currTetro, x, y)
        return if (field.isValidPosition(currTetro, x, y + 1)) {
            field.moveTetromino(currTetro, x, ++y)
            Status.DOWN
        } else {
            field.moveTetromino(currTetro, x, y)
            Status.NEXT
        }
    }

    fun moveLeft(): Status {
        field.clearTetromino(currTetro, x, y)
        if (field.isValidPosition(currTetro, x - 1, y)) {
            field.moveTetromino(currTetro, --x, y)
        } else {
            field.moveTetromino(currTetro, x, y)
        }
        return Status.DOWN
    }

    fun moveRight(): Status {
        field.clearTetromino(currTetro, x, y)
        if (field.isValidPosition(currTetro, x + 1, y)) {
            field.moveTetromino(currTetro, ++x, y)
        } else {
            field.moveTetromino(currTetro, x, y)
        }
        return Status.DOWN
    }

    fun rotate(): Status {
        field.clearTetromino(currTetro, x, y)
        currTetro.rotateRight()
        if (!field.isValidPosition(currTetro, x, y)) {
            currTetro.rotateLeft()
        }
        field.moveTetromino(currTetro, x, y)
        return Status.DOWN
    }
}

enum class Status {
    NEXT, DOWN, LEFT, RIGHT, ROTATE, GAME_OVER
}