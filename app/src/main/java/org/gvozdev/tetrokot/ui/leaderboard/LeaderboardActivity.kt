package org.gvozdev.tetrokot.ui.leaderboard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import org.gvozdev.tetrokot.data.Player
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

class LeaderboardActivity : ComponentActivity() {
    companion object {
        const val TAG = "Leaderboard_Activity"
    }

    //private lateinit var database: DatabaseReference
    //private var playerList = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //database = Firebase.database.getReference("players")
        //addPlayerEventListener(database, playerList)

        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LeaderboardView(
                        list = mutableListOf(
                            Player("Pro", 100000),
                            Player("Noob", 100)
                        )
                    )
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
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(listener)
    }
}