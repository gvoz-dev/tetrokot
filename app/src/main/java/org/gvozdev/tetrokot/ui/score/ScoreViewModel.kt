package org.gvozdev.tetrokot.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

@IgnoreExtraProperties
data class Player(val username: String? = null, val score: Int? = null)