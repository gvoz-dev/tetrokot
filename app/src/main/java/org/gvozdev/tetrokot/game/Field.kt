package org.gvozdev.tetrokot.game

class Field(width: Int, height: Int) {
    val border = 2
    val w = width + border * 2
    val h = height + border * 2
    val array = Array(h) { i: Int ->
        if (i < border || i >= h - border) IntArray(w) { -1 }
        else IntArray(w) { j: Int -> if (j < border || j >= w - border) -1 else 0 }
    }

    fun getSubArray(x: Int, y: Int, offsetX: Int, offsetY: Int): Array<IntArray> {
        val subArray = Array(offsetY) { IntArray(offsetX) }
        for (i in y until y + offsetY) {
            for (j in x until x + offsetX) {
                subArray[i - y][j - x] = array[i][j]
            }
        }
        return subArray
    }

    fun isValidPosition(tetromino: Tetromino, x: Int, y: Int): Boolean {
        if (x < 0 || y < 0 || x > w - tetromino.w || y > h - tetromino.h)
            return false
        val offsetX = x + tetromino.w
        val offsetY = y + tetromino.h
        for (i in y until offsetY)
            for (j in x until offsetX)
                if (tetromino.mask[i - y][j - x] != 0 && array[i][j] != 0) {
                    return false
                }
        return true
    }

    fun moveTetromino(tetromino: Tetromino, x: Int, y: Int) {
        val offsetX = x + tetromino.w
        val offsetY = y + tetromino.h
        for (i in y until offsetY) {
            for (j in x until offsetX) {
                if (tetromino.mask[i - y][j - x] != 0) {
                    array[i][j] = tetromino.mask[i - y][j - x]
                }
            }
        }
    }

    fun clearTetromino(tetromino: Tetromino, x: Int, y: Int) {
        val offsetX = x + tetromino.w
        val offsetY = y + tetromino.h
        for (i in y until offsetY) {
            for (j in x until offsetX) {
                if (tetromino.mask[i - y][j - x] != 0) {
                    array[i][j] = array[i][j] xor tetromino.mask[i - y][j - x]
                }
            }
        }
    }

    private fun checkFilledRow(row: Int): Boolean {
        for (col in border until w - border) {
            if (array[row][col] == 0) {
                return false
            }
        }
        return true
    }

    private fun removeRow(row: Int) {
        for (i in row downTo border + 1) {
            array[i] = array[i - 1]
        }
        array[border] = IntArray(w) { if (it < border || it >= w - border) -1 else 0 }
    }

    fun removeFilledRows(): Int {
        var rowCounter = 0
        var row = h - border - 1
        while (row > 2) {
            if (checkFilledRow(row)) {
                removeRow(row++)
                if (++rowCounter == 4) break
            }
            row--
        }
        return when (rowCounter) {
            1 -> 40
            2 -> 100
            3 -> 300
            4 -> 1200
            else -> 0
        }
    }
}