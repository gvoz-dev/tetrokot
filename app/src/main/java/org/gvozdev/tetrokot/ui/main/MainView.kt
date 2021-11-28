package org.gvozdev.tetrokot.ui.main

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.gvozdev.tetrokot.ui.game.GameActivity
import org.gvozdev.tetrokot.ui.settings.SettingsActivity
import org.gvozdev.tetrokot.ui.theme.*

@Composable
fun MainView() {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            "Tetrokot"
        )
        MainMenu(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6.0f)
        )
    }
}

@Composable
fun Title(modifier: Modifier = Modifier, text: String) {
    Box(modifier.background(TitleBackgroundColor), Alignment.Center) {
        Text(
            text = text,
            color = TitleTextColor,
            fontSize = TitleFontSize,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as? Activity
    Column(
        modifier.background(MenuBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuButton("Start") { activity?.startActivity(Intent(activity, GameActivity::class.java)) }
        MenuButton("Leaderboard") { /*TODO*/ }
        MenuButton("Settings") {
            activity?.startActivity(
                Intent(
                    activity,
                    SettingsActivity::class.java
                )
            )
        }
        //MenuButton("Exit") { activity?.finish() }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick,
        modifier = Modifier
            .width(ButtonWidth)
            .height(ButtonHeight)
            .padding(ButtonPadding),
        colors = ButtonDefaults.buttonColors(ButtonBackgroundColor, ButtonTextColor),
        shape = Shapes.large
    ) {
        Text(text, fontSize = ButtonFontSize, fontFamily = FontFamily.Serif)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainView()
        }
    }
}