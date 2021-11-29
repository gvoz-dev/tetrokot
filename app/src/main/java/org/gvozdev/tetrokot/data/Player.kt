package org.gvozdev.tetrokot.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Player(val username: String? = null, val score: Int? = null)