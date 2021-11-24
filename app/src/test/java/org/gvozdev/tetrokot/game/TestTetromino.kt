package org.gvozdev.tetrokot.game

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestTetromino {
    private lateinit var tetromino: Tetromino

    @Before
    fun setUp() {
        tetromino = Tetromino(Tetromino.Type.TEST)
    }

    @Test
    fun testInitialVerticalOffset() {
        val tI = Tetromino(Tetromino.Type.I)
        val tJ = Tetromino(Tetromino.Type.J)
        val border = 2
        assertEquals(1, tI.initialVerticalOffset(border))
        assertEquals(2, tJ.initialVerticalOffset(border))
    }

    @Test
    fun testInitialHorizontalOffset() {
        val tI = Tetromino(Tetromino.Type.I)
        val tL = Tetromino(Tetromino.Type.L)
        val width = 14
        assertEquals(5, tI.initialHorizontalOffset(width))
        assertEquals(6, tL.initialHorizontalOffset(width))
    }

    @Test
    fun testRotateRight() {
        val expectedMask = arrayOf(
            intArrayOf(3, 0, 0, 1),
            intArrayOf(0, 2, 2, 0),
            intArrayOf(0, 2, 2, 0),
            intArrayOf(3, 0, 0, 1)
        )
        tetromino.rotateRight()
        assertArrayEquals(expectedMask, tetromino.mask)
    }

    @Test
    fun testRotateLeft() {
        val expectedMask = arrayOf(
            intArrayOf(1, 0, 0, 3),
            intArrayOf(0, 2, 2, 0),
            intArrayOf(0, 2, 2, 0),
            intArrayOf(1, 0, 0, 3)
        )
        tetromino.rotateLeft()
        assertArrayEquals(expectedMask, tetromino.mask)
    }
}