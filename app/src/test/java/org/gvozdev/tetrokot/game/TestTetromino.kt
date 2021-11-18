package org.gvozdev.tetrokot.game

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestTetromino {
    private lateinit var tetromino: Tetromino

    @Before
    fun setUp() {
        tetromino = Tetromino(Tetromino.Type.OTHER)
    }

    @Test
    fun testRotateRight() {
        assertEquals(5, tetromino.h)
        assertEquals(4, tetromino.w)
        val expectedMask = arrayOf(
            intArrayOf(6, 0, 0, 1, 0),
            intArrayOf(5, 2, 2, 1, 0),
            intArrayOf(4, 7, 7, 1, 0),
            intArrayOf(3, 0, 0, 1, 0)
        )
        tetromino.rotateRight()
        assertArrayEquals(expectedMask, tetromino.mask)
        assertEquals(4, tetromino.h)
        assertEquals(5, tetromino.w)
    }

    @Test
    fun testRotateLeft() {
        assertEquals(5, tetromino.h)
        assertEquals(4, tetromino.w)
        val expectedMask = arrayOf(
            intArrayOf(0, 1, 0, 0, 3),
            intArrayOf(0, 1, 7, 7, 4),
            intArrayOf(0, 1, 2, 2, 5),
            intArrayOf(0, 1, 0, 0, 6)
        )
        tetromino.rotateLeft()
        assertArrayEquals(expectedMask, tetromino.mask)
        assertEquals(4, tetromino.h)
        assertEquals(5, tetromino.w)
    }
}