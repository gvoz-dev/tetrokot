package org.gvozdev.tetrokot.game

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TestActionValidator {
    @Test
    fun `test isValidAction`() {
        val field = Field(10, 20)
        val tetromino = Tetromino(Tetromino.Type.I)
        assertTrue(isValidAction(5, 5, tetromino, field))
        assertFalse(isValidAction(-1, -1, tetromino, field))
        assertFalse(isValidAction(100, 100, tetromino, field))
        assertFalse(isValidAction(0, 0, tetromino, field))
    }
}