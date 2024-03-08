package userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.AccountManager


class LockPage: Page() {
    lateinit var onLogin: (username: String, pwd: String) -> Boolean
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember { mutableStateOf("") }
            var pwd by remember { mutableStateOf("") }
            var errorText by remember { mutableStateOf("") }

            Text("Playing lullabies")
            Text("Up next......")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldFormat(name = username, isPwd = false, onNameChange = { if (it.length <= 50) username = it }, "Account Email")
            TextFieldFormat(name = pwd, isPwd = true, onNameChange = { if (it.length <= 50) pwd = it }, "Account Password")
            Button(onClick = {
                val tryLoggingIn = onLogin(username, pwd);
                if (!tryLoggingIn) {
                    errorText = "Email/password combination is invalid. Try Again"
                }
            }) {
                Text("Unlock Application (Return to Explore)")
            }

            Text(errorText)
        }
    }
}