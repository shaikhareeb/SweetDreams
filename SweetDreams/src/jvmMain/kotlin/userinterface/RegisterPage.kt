package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class RegisterPage : Page() {
    lateinit var onReturn : (fname: String, lname: String, userName: String, email: String, pwd: String, pwdVerfication: String) -> String
    lateinit var onBack : () -> Unit
    @Composable
    override fun Content(){
        var fname by remember { mutableStateOf("") }
        var lname by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }
        var pwdVerification by remember { mutableStateOf("") }

        var errorText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign up for SweetDreams")
            TextFieldFormat(name = fname, isPwd = false, onNameChange = { if (it.length <= 50) fname = it }, "First Name")
            TextFieldFormat(name = lname, isPwd = false, onNameChange = { if (it.length <= 50) lname = it }, "Last Name")
            TextFieldFormat(name = userName, isPwd = false, onNameChange = { if (it.length <= 50) userName = it }, "Username")
            TextFieldFormat(name = email, isPwd = false, onNameChange = { if (it.length <= 50) email = it }, "Email")
            TextFieldFormat(name = pwd, isPwd = true, onNameChange = { if (it.length <= 50) pwd = it }, "Password")
            TextFieldFormat(name = pwdVerification, isPwd = true, onNameChange = { if (it.length <= 50) pwdVerification = it }, "Re-enter Password")
            Button(onClick = {
                val tryReturning = onReturn(fname, lname, userName, email, pwd, pwdVerification);
                if (tryReturning != "") {
                    errorText = tryReturning
                }
                 }) {
                Text("Register")
            }
            Button(onClick = onBack) {
                Text("Go Back")
            }

            Text(errorText)
        }
    }
}