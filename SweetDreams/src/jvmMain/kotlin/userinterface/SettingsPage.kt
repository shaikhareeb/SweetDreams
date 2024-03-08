package userinterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SettingsPage: Page() {
    lateinit var navBar : NavBar
    lateinit var onDelete : () -> Unit
    lateinit var onReset : (String) -> Unit


    @Composable
    override fun Content(){
        var sliderPosition = 100.toFloat()
        var pwd by remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray)) {
            // NavBar on the left with its own outline
            Column(
                modifier = Modifier.width(200.dp).fillMaxHeight().border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Top
            ) {
                navBar.nav()
            }

            // Main content area on the right with its own outline
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Language: English")
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },

                    )
                Text(text = "Volume")
                TextFieldFormat(
                    name = pwd,
                    isPwd = false,
                    onNameChange = { if (it.length <= 50) pwd = it },
                    "New Password"
                )

                Button(onClick = {
                    onDelete()
                }) {
                    Text("Delete Account")
                }
                Button(onClick = {
                    onReset
                    (pwd)
                }) {
                    Text("Reset Password")
                }
            }
        }
    }
}