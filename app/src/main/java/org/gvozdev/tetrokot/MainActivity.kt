package org.gvozdev.tetrokot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gvozdev.tetrokot.ui.theme.TetrokotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetrokotTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        )
        MainMenu(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6.0f)
        )
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    val backgroundColor = Color(0xFF512DA8)
    val textColor = Color(0xFFB3E5FC)
    Box(modifier.background(backgroundColor), Alignment.Center) {
        Text(
            text = "Tetrokot",
            color = textColor,
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val backgroundColor = Color(0xFFE0F7FA)
    val activity = LocalContext.current as? Activity
    Column(
        modifier.background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuButton("Start") { activity?.startActivity(Intent(activity, GameActivity::class.java)) }
        MenuButton("Leaderboard") { /*TODO*/ }
        MenuButton("Settings") { /*TODO*/ }
        MenuButton("Exit") { activity?.finish() }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    val backgroundColor = Color(0xFF512DA8)
    val textColor = Color(0xFFB3E5FC)
    Button(
        onClick,
        modifier = Modifier
            .width(340.dp)
            .height(120.dp)
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor, textColor),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text, fontSize = 30.sp, fontFamily = FontFamily.Serif)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen()
        }
    }
}