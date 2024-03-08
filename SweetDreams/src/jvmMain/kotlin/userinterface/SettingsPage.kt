package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class SettingsPage: Page() {
    lateinit var navBar : NavBar
    lateinit var onDelete : () -> Unit
    lateinit var onReset : (String) -> Unit


    @Composable
    override fun Content(){
        var sliderPosition = 100.toFloat()
        var pwd by remember { mutableStateOf("") }
        navBar.nav()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Language: English")
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },

            )
            Text(text = "Volume")
            TextFieldFormat(name = pwd, isPwd = false, onNameChange = { if (it.length <= 50) pwd = it }, "New Password")

            Button(onClick = {
                onDelete()
            }) {
                Text("Delete Account")
            }
            Button(onClick = {onReset
                (pwd)}) {
                Text("Reset Password")
            }

        }
    }
}