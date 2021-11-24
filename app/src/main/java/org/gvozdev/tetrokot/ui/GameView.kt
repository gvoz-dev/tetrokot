package org.gvozdev.tetrokot.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.gvozdev.tetrokot.game.GameState
import org.gvozdev.tetrokot.game.GameViewModel
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme
import kotlin.math.min

@Composable
fun GameView() {
    val viewModel = GameViewModel()
    val state by viewModel.gameState

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameInfo(state)
            GameField(state)
        }
    }
}

@Composable
fun GameInfo(state: GameState, modifier: Modifier = Modifier) {
    val height = 44.dp
    Row(
        modifier = modifier.fillMaxWidth().height(height),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Next: ")
            Canvas(Modifier.width(height).height(height)) {
                val blockSize =
                    min(size.width / state.next.w.toFloat(), size.height / state.next.h.toFloat())
                for (i in state.next.mask.indices) {
                    for (j in state.next.mask[0].indices) {
                        drawBlock(j, i, blockSize, getBlockColor(state.next.mask[i][j]))
                    }
                }
            }
        }
        Text(text = "Score: ${state.score}")
    }
}

@Composable
fun GameField(state: GameState, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val blockSize =
            min(size.width / state.field.w.toFloat(), size.height / state.field.h.toFloat())
        for (i in state.field.array.indices) {
            for (j in state.field.array[0].indices) {
                drawBlock(j, i, blockSize, getBlockColor(state.field.array[i][j]))
            }
        }
    }
}

fun DrawScope.drawBlock(x: Int, y: Int, blockSize: Float, blockColor: Color) {
    val borderWidth = blockSize / 20
    drawRoundRect(
        color = blockColor,
        topLeft = Offset(x * blockSize + borderWidth, y * blockSize + borderWidth),
        size = Size(blockSize - 2 * borderWidth, blockSize - 2 * borderWidth),
        cornerRadius = CornerRadius(14F, 14F)
    )
}

fun getBlockColor(block: Int): Color = when (block) {
    0 -> Color(0xFFE0F7FA)
    1 -> Color(0xFF1DE9B6)
    2 -> Color(0xFFFFEA00)
    3 -> Color(0xFFD500F9)
    4 -> Color(0xFF3D5AFE)
    5 -> Color(0xFFFF3D00)
    6 -> Color(0xFF76FF03)
    7 -> Color(0xFFF50057)
    else -> Color(0xFF512DA8)
}

@Preview(showBackground = true)
@Composable
fun DefaultGamePreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            GameView()
        }
    }
}