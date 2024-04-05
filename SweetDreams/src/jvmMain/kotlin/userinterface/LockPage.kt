package userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.AccountManager


class LockPage: Page() {
    lateinit var onLogin: (username: String, pwd: String) -> Boolean

    lateinit var audioBar: AudioBar

    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = {
                Spacer(modifier = Modifier.width(200.dp))
                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.1F)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black)
                        .background(Color(0xFF93AEDE))
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                ) {
                    audioBar.audioplayer()
                }
            }
        )
        {
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0xFF93AEDE)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                var errorText by remember { mutableStateOf("") }

                Text("Playing lullabies")
                Text("Up next......")
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldFormat(
                    name = username,
                    isPwd = false,
                    onNameChange = { if (it.length <= 50) username = it },
                    "Account Email"
                )
                TextFieldFormat(
                    name = pwd,
                    isPwd = true,
                    onNameChange = { if (it.length <= 50) pwd = it },
                    "Account Password"
                )
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    val tryLoggingIn = onLogin(username, pwd);
                    if (!tryLoggingIn) {
                        errorText = "Email/password combination is invalid. Try Again"
                    }
                }) {
                    Text("Unlock Application (Return to Explore)", color = Color.White)
                }

                Text(errorText)
            }
        }
    }
}