package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Username")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Password")

            Button(onClick = { onLogin() }) {
                Text("Login")
            }
            Button(onClick = { onSignUp() }) {
                Text("Sign up")
            }
        }
    }

    @Composable
    fun TextFieldFormat(name: String, onNameChange: (String) -> Unit, title: String) {
        Column(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
        }
    }

    @Composable
    fun SimpleImage() {
        Image(
            painter = painterResource("Picture1.png"),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
        )
    }

}