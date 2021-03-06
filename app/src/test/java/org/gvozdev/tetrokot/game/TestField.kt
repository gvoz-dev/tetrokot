package org.gvozdev.tetrokot.game

import org.junit.Assert
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestField {
    private lateinit var field: Field

    @Before
    fun setUp() {
        field = Field(10, 20)
    }

    @Test
    fun testInit() {
        for (i in 0 until field.h) {
            for (j in 0 until field.w) {
                if (i !in field.border until field.h - field.border ||
                    j !in field.border until field.w - field.border
                ) {
                    assertEquals(-1, field.array[i][j])
                } else {
                    assertEquals(0, field.array[i][j])
                }
            }
        }
    }

    @Test
    fun testGetSubArray() {
        val expected = arrayOf(
            intArrayOf(-1, -1, -1),
            intArrayOf(-1, -1, -1),
            intArrayOf(-1, -1, 0)
        )
        assertArrayEquals(expected, field.getSubArray(0, 0, 3, 3))
    }

    @Test
    fun testIsValidPosition() {
        val tetromino = Tetromino(Tetromino.Type.Z)
        Assert.assertTrue(field.isValidPosition(tetromino, 5, 5))
        Assert.assertFalse(field.isValidPosition(tetromino, -1, -1))
        Assert.assertFalse(field.isValidPosition(tetromino, 100, 100))
        Assert.assertFalse(field.isValidPosition(tetromino, 0, 0))
    }

    @Test
    fun testMoveTetromino() {
        val tetromino = Tetromino(Tetromino.Type.T)
        val p = field.border
        field.moveTetromino(tetromino, p, p)
        assertArrayEquals(tetromino.mask, field.getSubArray(p, p, 3, 3))
    }

    @Test
    fun testClearTetromino() {
        val expected = Array(3) { IntArray(3) { 0 } }
        val tetromino = Tetromino(Tetromino.Type.S)
        val p = field.border
        field.moveTetromino(tetromino, p, p)
        field.clearTetromino(tetromino, p, p)
        assertArrayEquals(expected, field.getSubArray(p, p, 3, 3))
    }

    @Test
    fun testCheckRow() {
        val tetromino = Tetromino(Tetromino.Type.O)
        val p = field.border
        field.moveTetromino(tetromino, 2, p)
        field.moveTetromino(tetromino, 4, p)
        field.moveTetromino(tetromino, 6, p)
        field.moveTetromino(tetromino, 8, p)
        field.moveTetromino(tetromino, 10, p)
        val score = field.removeFilledRows()
        assertEquals(100, score)
        val expectedField = Field(10, 20)
        assertArrayEquals(expectedField.array, field.array)
    }
}