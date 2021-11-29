package org.gvozdev.tetrokot.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.gvozdev.tetrokot.data.Player

class ScoreViewModel : ViewModel() {
    private lateinit var database: DatabaseReference

    fun send(username: String?, score: Int?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val player = Player(username, score)
                database = Firebase.database.reference
                database.child("players").push().setValue(player)
            }
        }
    }
}