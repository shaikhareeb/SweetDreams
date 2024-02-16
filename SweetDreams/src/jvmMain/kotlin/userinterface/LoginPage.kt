package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class LoginPage : Page() {
    lateinit var onSignUp : () -> Unit
    lateinit var onLogin: () -> Unit
    @Composable
    override fun Content(){
        var name by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to SweetDreams")
            HelloContent(name = name, onNameChange = { if (it.length <= 50) name = it }, "Username")
            HelloContent(name = name, onNameChange = { if (it.length <= 50) name = it }, "Password")

            Button(onClick = { onLogin() }) {
                Text("Login")
            }
            Button(onClick = { onSignUp() }) {
                Text("Sign up")
            }
        }
    }

    @Composable
    fun HelloContent(name: String, onNameChange: (String) -> Unit, title: String) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
        }
    }


}