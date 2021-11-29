package org.gvozdev.tetrokot.data

import com.google.firebase.database.IgnoreExtraProperties

const val PLAYERS_FB_PATH = "players"

@IgnoreExtraProperties
data class Player(val username: String? = null, val score: Int? = null)