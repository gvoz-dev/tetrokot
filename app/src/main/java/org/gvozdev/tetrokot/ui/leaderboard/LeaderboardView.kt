package org.gvozdev.tetrokot.ui.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.gvozdev.tetrokot.data.Player
import org.gvozdev.tetrokot.ui.theme.PlayerCardBackgroundColor
import org.gvozdev.tetrokot.ui.theme.Shapes
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

@Composable
fun LeaderboardView(list: List<Player>) {
    var playerList by remember { mutableStateOf(list) }
    val listState = rememberLazyListState()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(playerList) { item ->
                PlayerCard(player = item)
            }
        }
    }
}

@Composable
fun PlayerCard(player: Player) {
    val userName = player.username ?: "Error"
    val score = "${player.score ?: -1}"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp)
            .background(PlayerCardBackgroundColor, Shapes.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = userName, modifier = Modifier.padding(20.dp))
        Text(text = score, modifier = Modifier.padding(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LeaderboardPreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            LeaderboardView(list = mutableListOf(Player(), Player()))
        }
    }
}