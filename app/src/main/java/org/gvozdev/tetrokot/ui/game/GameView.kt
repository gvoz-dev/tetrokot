package org.gvozdev.tetrokot.ui.game

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.gvozdev.tetrokot.game.GameState
import org.gvozdev.tetrokot.game.Status
import org.gvozdev.tetrokot.ui.score.ScoreActivity
import org.gvozdev.tetrokot.ui.theme.GameInfoHeight
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme
import org.gvozdev.tetrokot.ui.theme.getBlockColor
import kotlin.math.min

@Composable
fun GameView() {
    val viewModel = viewModel<GameViewModel>()
    val game by viewModel.game.collectAsState()
    val activity = LocalContext.current as? Activity

    LaunchedEffect(Unit) {
        while (true) {
            delay(300L)
            viewModel.update(GameTick)
            if (game.status == Status.GAME_OVER) {
                val intent = Intent(activity, ScoreActivity::class.java)
                intent.putExtra("score", game.score)
                activity?.startActivity(intent)
                activity?.finish()
                break
            }
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
                        detectHorizontalDragGestures { _, dragAmount ->
                            when (dragAmount) {
                                in 20F..40F -> viewModel.update(MoveRight)
                                in -40F..-20F -> viewModel.update(MoveLeft)
                            }
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures { viewModel.update(Rotate) }
                    }
            )
        }
    }
}

@Composable
fun GameInfo(game: GameState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(GameInfoHeight),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Next:\t")
            Canvas(
                Modifier
                    .width(GameInfoHeight / 1.2F)
                    .height(GameInfoHeight / 1.2F)
            ) {
                val blockSize =
                    min(size.width / game.nextTetro.w, size.height / game.nextTetro.h)
                val mask = game.nextTetro.mask
                for (i in mask.indices) {
                    for (j in mask[0].indices) {
                        drawBlock(j, i, blockSize, getBlockColor(mask[i][j]), 8F)
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
        val blockSize = min(size.width / game.field.w, size.height / game.field.h)
        for (i in 1 until game.field.h - 1) {
            for (j in 1 until game.field.w - 1) {
                drawBlock(j, i, blockSize, getBlockColor(game.field.array[i][j]), 15F)
            }
        }
    }
}

fun DrawScope.drawBlock(x: Int, y: Int, blockSize: Float, blockColor: Color, corner: Float) {
    val borderWidth = blockSize / 20
    drawRoundRect(
        color = blockColor,
        topLeft = Offset(x * blockSize + borderWidth, y * blockSize + borderWidth),
        size = Size(blockSize - 2 * borderWidth, blockSize - 2 * borderWidth),
        cornerRadius = CornerRadius(corner, corner)
    )
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