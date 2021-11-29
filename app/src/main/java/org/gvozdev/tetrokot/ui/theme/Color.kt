package org.gvozdev.tetrokot.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val TitleBackgroundColor = Color(0xFF311B92)
val TitleTextColor = Color(0xFFE8EAF6)

val MenuBackgroundColor = Color(0xFFE8EAF6)

val ButtonBackgroundColor = Color(0xFF311B92)
val ButtonTextColor = Color(0xFFE8EAF6)

val TextColor = Color(0xFF311B92)

val PlayerCardBackgroundColor = Color(0xFF9FA8DA)

val EmptyBlock = Color(0xFFE8EAF6)
val BlockI = Color(0xFF1DE9B6)
val BlockO = Color(0xFFFFEA00)
val BlockT = Color(0xFFD500F9)
val BlockJ = Color(0xFF3D5AFE)
val BlockL = Color(0xFFFF9100)
val BlockS = Color(0xFF76FF03)
val BlockZ = Color(0xFFF50057)
val BorderBlock = Color(0xFF311B92)

fun getBlockColor(block: Int): Color = when (block) {
    0 -> EmptyBlock
    1 -> BlockI
    2 -> BlockO
    3 -> BlockT
    4 -> BlockJ
    5 -> BlockL
    6 -> BlockS
    7 -> BlockZ
    else -> BorderBlock
}