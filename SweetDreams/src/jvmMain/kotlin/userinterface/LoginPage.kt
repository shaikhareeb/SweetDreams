package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class LoginPage : Page() {
    lateinit var onSignUp : () -> Unit
    lateinit var onLogin: (username: String, pwd: String) -> Boolean
    @Composable
    override fun Content(){
        var username by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }

        var errorText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleImage()
            TextFieldFormat(name = username, isPwd = false, onNameChange = { if (it.length <= 50) username = it }, "Email")
            TextFieldFormat(name = pwd, isPwd = true, onNameChange = { if (it.length <= 50) pwd = it }, "Password")

            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                val tryLoggingIn = onLogin(username, pwd);
                if (!tryLoggingIn) {
                    errorText = "Email/password combination is invalid. Try Again"
                }
            }) {
                Text("Login", color = Color.White)
            }
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = { onSignUp() }) {
                Text("Sign up", color = Color.White)
            }

            Text(errorText)
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