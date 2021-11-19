package org.gvozdev.tetrokot.game

fun isValidAction(x: Int, y: Int, tetromino: Tetromino, field: Field): Boolean {
    if (x < 0 || y < 0 || x > field.w - tetromino.w || y > field.h - tetromino.h)
        return false
    val offsetX = x + tetromino.w
    val offsetY = y + tetromino.h
    for (i in y until offsetY)
        for (j in x until offsetX)
            if (tetromino.mask[i - y][j - x] != 0 && field.array[i][j] != 0) {
                return false
            }
    return true
}