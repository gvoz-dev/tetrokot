package org.gvozdev.tetrokot.game

class Field(width: Int, height: Int) {
    val wall = 2
    val w = width + wall * 2
    val h = height + wall * 2
    val array = Array(h) { IntArray(w) { -1 } }

    init {
        for (i in wall until h - wall) {
            for (j in wall until w - wall) {
                array[i][j] = 0
            }
        }
    }

    fun getSubArray(x: Int, y: Int, dx: Int, dy: Int): Array<IntArray> {
        val subArray = Array(dy) { IntArray(dx) }
        for (i in y until y + dy) {
            for (j in x until x + dx) {
                subArray[i - y][j - x] = array[i][j]
            }
        }
        return subArray
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

    private fun checkFilledRow(i: Int): Boolean {
        for (j in wall until w - wall) {
            if (array[i][j] == 0) {
                return false
            }
        }
        return true
    }

    private fun removeRow(row: Int) {
        for (i in row downTo wall + 1) {
            array[i] = array[i - 1]
        }
        array[wall] = IntArray(w) { -1 }
        for (j in wall until w - wall) {
            array[wall][j] = 0
        }
    }

    fun checkRows() {
        var row = h - wall - 1
        while (row > 2) {
            if (checkFilledRow(row)) {
                removeRow(row++)
            }
            row--
        }
    }
}