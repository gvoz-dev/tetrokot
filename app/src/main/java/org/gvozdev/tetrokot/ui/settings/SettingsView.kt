package org.gvozdev.tetrokot.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.gvozdev.tetrokot.ui.main.MenuButton
import org.gvozdev.tetrokot.ui.main.Title
import org.gvozdev.tetrokot.ui.theme.*

@Composable
fun SettingsView() {
    Column {
        Title(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            "Settings"
        )
        Settings(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6.0f)
        )
    }
}

@Composable
fun Settings(modifier: Modifier = Modifier) {
    Column(
        modifier.background(MenuBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FieldSettings()
        DifficultySettings()
        MenuButton("Apply") { /*TODO*/ }
    }
}

@Composable
fun FieldSettings(modifier: Modifier = Modifier) {
    val sizes = listOf("10 × 20", "14 × 28")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sizes[0]) }
    Column(
        modifier.background(MenuBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Field size",
            color = TextColor,
            fontSize = FontSize,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
        Column(Modifier.selectableGroup()) {
            sizes.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(RadioButtonHeight)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(selectedColor = ButtonBackgroundColor)
                    )
                    Text(text = text, fontSize = FontSize, fontFamily = FontFamily.Serif)
                }
            }
        }
    }
}

@Composable
fun DifficultySettings(modifier: Modifier = Modifier) {
    val difficulties = listOf("Easy", "Medium", "Hard")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(difficulties[0]) }
    Column(
        modifier.background(MenuBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Difficulty",
            color = TextColor,
            fontSize = FontSize,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
        Column(Modifier.selectableGroup()) {
            difficulties.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(RadioButtonHeight)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(selectedColor = ButtonBackgroundColor)
                    )
                    Text(text = text, fontSize = FontSize, fontFamily = FontFamily.Serif)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSettingsPreview() {
    TetrokotTheme {
        Surface(color = MaterialTheme.colors.background) {
            SettingsView()
        }
    }
}