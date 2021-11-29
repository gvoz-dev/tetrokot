package org.gvozdev.tetrokot.ui.leaderboard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.gvozdev.tetrokot.data.PLAYERS_FB_PATH
import org.gvozdev.tetrokot.data.Player
import org.gvozdev.tetrokot.data.ScoreComparator
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

class LeaderboardActivity : ComponentActivity() {
    private lateinit var database: DatabaseReference
    private var players = mutableStateListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.getReference(PLAYERS_FB_PATH)
        addPlayerEventListener(database, players)

        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LeaderboardView(players)
                }
            }
        }
    }

    private fun addPlayerEventListener(
        database: DatabaseReference,
        playerList: MutableList<Player>
    ) {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val player = ds.getValue<Player>()
                    if (player != null) {
                        playerList.add(player)
                    }
                }
                playerList.sortWith(ScoreComparator())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Leaderboard", "onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(listener)
    }
}