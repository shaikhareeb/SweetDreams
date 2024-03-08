package userinterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class SettingsPage: Page() {
    lateinit var navBar : NavBar
    lateinit var onDelete : (String) -> String
    lateinit var onReset : (String) -> Unit


    @Composable
    override fun Content(){
        var sliderPosition = 100.toFloat()
        var uid by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }
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
                TextFieldFormat(name = uid, isPwd = false, onNameChange = { if (it.length <= 50) uid = it }, "Re-enter id to delete account")
                Button(onClick = {
                    errorText = onDelete(uid)
                }) {
                    Text("Delete Account")
                }
                TextFieldFormat(name = pwd, isPwd = true, onNameChange = { if (it.length <= 50) pwd = it }, "New Password")
                Button(onClick = {
                    onReset
                    (pwd)
                }) {
                    Text("Reset Password")
                }
                Text(errorText)
            }
        }
    }
}