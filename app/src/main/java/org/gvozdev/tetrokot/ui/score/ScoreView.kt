package org.gvozdev.tetrokot.ui.score

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.gvozdev.tetrokot.ui.game.GameActivity
import org.gvozdev.tetrokot.ui.main.MainActivity
import org.gvozdev.tetrokot.ui.main.MenuButton
import org.gvozdev.tetrokot.ui.main.Title
import org.gvozdev.tetrokot.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScoreView(score: Int) {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            "Score: $score"
        )
        ScoreInput(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6.0f),
            score
        )
    }
}

@Composable
fun ScoreInput(modifier: Modifier = Modifier, score: Int) {
    val activity = LocalContext.current as? Activity
    val date = SimpleDateFormat("dd/MM/yyyy_HH:mm:ss", Locale.US).format(Date())
    val name = remember { mutableStateOf("Tester_$date") }
    Column(
        modifier.background(MenuBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your name",
            color = TextColor,
            fontSize = FontSize,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = name.value,
            onValueChange = { newText -> name.value = newText },
            modifier = Modifier
                .width(ButtonWidth)
                .height(ButtonHeight)
                .padding(ButtonPadding)
        )
        MenuButton("Send") {
            if (score < 40) {
                Toast.makeText(activity, "Your score < 40", Toast.LENGTH_LONG).show()
            } else {
                ScoreViewModel().send(name.value, score)
            }
            activity?.startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
        MenuButton("Restart") {
            activity?.startActivity(Intent(activity, GameActivity::class.java))
            activity?.finish()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScorePreview() {
    TetrokotTheme {
        ScoreView(1000000)
    }
}