package org.gvozdev.tetrokot.game

import kotlin.random.Random

// https://en.wikipedia.org/wiki/Tetromino
class Tetromino(val type: Type) {
    var mask: Array<IntArray>
    var w: Int
    var h: Int

    init {
        mask = when (type) {
            Type.I -> arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0)
            )
            Type.O -> arrayOf(
                intArrayOf(2, 2),
                intArrayOf(2, 2)
            )
            Type.T -> arrayOf(
                intArrayOf(0, 3, 0),
                intArrayOf(3, 3, 3),
                intArrayOf(0, 0, 0)
            )
            Type.J -> arrayOf(
                intArrayOf(4, 0, 0),
                intArrayOf(4, 4, 4),
                intArrayOf(0, 0, 0)
            )
            Type.L -> arrayOf(
                intArrayOf(0, 0, 5),
                intArrayOf(5, 5, 5),
                intArrayOf(0, 0, 0)
            )
            Type.S -> arrayOf(
                intArrayOf(0, 6, 6),
                intArrayOf(6, 6, 0),
                intArrayOf(0, 0, 0)
            )
            Type.Z -> arrayOf(
                intArrayOf(7, 7, 0),
                intArrayOf(0, 7, 7),
                intArrayOf(0, 0, 0)
            )
            Type.OTHER -> arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(0, 2, 7, 0),
                intArrayOf(0, 2, 7, 0),
                intArrayOf(6, 5, 4, 3)
            )
        }
        w = mask[0].size
        h = mask.size
    }

    enum class Type {
        I, O, T, J, L, S, Z, OTHER
    }

    companion object {
        val next: Tetromino
            get() = when (Random.nextInt(0, 7)) {
                0 -> Tetromino(Type.I)
                1 -> Tetromino(Type.O)
                2 -> Tetromino(Type.T)
                3 -> Tetromino(Type.J)
                4 -> Tetromino(Type.L)
                5 -> Tetromino(Type.S)
                6 -> Tetromino(Type.Z)
                else -> Tetromino(Type.OTHER)
            }
    }

    fun rotateRight() {
        val rotated = Array(w) { IntArray(h) }
        for (i in 0 until w) {
            for (j in 0 until h) {
                rotated[i][j] = mask[h - j - 1][i]
            }
        }
        mask = rotated
        w = h.also { h = w }
    }

    fun rotateLeft() {
        val rotated = Array(w) { IntArray(h) }
        for (i in 0 until w) {
            for (j in 0 until h) {
                rotated[i][j] = mask[j][w - i - 1]
            }
        }
        mask = rotated
        w = h.also { h = w }
    }
}