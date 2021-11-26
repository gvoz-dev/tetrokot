package org.gvozdev.tetrokot.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.gvozdev.tetrokot.game.*
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme
import kotlin.math.min
import kotlin.math.sqrt


@Composable
fun GameView() {
    val viewModel = viewModel<GameViewModel>()
    val game by viewModel.game.collectAsState()

    LaunchedEffect(Unit) {
        while (true) {
            delay(300)
            viewModel.update(GameTick)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameInfo(game)
            GameField(game,
                Modifier
                    .pointerInput(Unit) {
                        detectTapGestures { viewModel.update(Rotate) }
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            val (x, y) = dragAmount
                            if (sqrt(x * x + y * y) < 40) return@detectDragGestures
                            when {
                                x > 40 -> viewModel.update(MoveRight)
                                x < -40 -> viewModel.update(MoveLeft)
                            }
                        }
                    })
        }
    }
}

@Composable
fun GameInfo(game: GameState, modifier: Modifier = Modifier) {
    val height = 46.dp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Next:\t")
            Canvas(
                Modifier
                    .width(height / 1.2F)
                    .height(height / 1.2F)
            ) {
                val blockSize =
                    min(
                        size.width / game.nextTetro.w.toFloat(),
                        size.height / game.nextTetro.h.toFloat()
                    )
                for (i in game.nextTetro.mask.indices) {
                    for (j in game.nextTetro.mask[0].indices) {
                        drawBlock(j, i, blockSize, getBlockColor(game.nextTetro.mask[i][j]))
                    }
                }
            }
        }
        Text(text = "Score: ${game.score}")
    }
}

@Composable
fun GameField(game: GameState, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val blockSize =
            min(size.width / game.field.w.toFloat(), size.height / game.field.h.toFloat())
        for (i in 1 until game.field.h - 1) {
            for (j in 1 until game.field.w - 1) {
                drawBlock(j, i, blockSize, getBlockColor(game.field.array[i][j]))
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
        cornerRadius = CornerRadius(12F, 12F)
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