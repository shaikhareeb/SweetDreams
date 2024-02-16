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
            TextFieldFormat(name = fname, onNameChange = { if (it.length <= 50) fname = it }, "First Name")
            TextFieldFormat(name = lname, onNameChange = { if (it.length <= 50) lname = it }, "Last Name")
            TextFieldFormat(name = userName, onNameChange = { if (it.length <= 50) userName = it }, "Username")
            TextFieldFormat(name = email, onNameChange = { if (it.length <= 50) email = it }, "Email")
            TextFieldFormat(name = pwd, onNameChange = { if (it.length <= 50) pwd = it }, "Password")
            TextFieldFormat(name = pwdVerification, onNameChange = { if (it.length <= 50) pwdVerification = it }, "Re-enter Password")
            Button(onClick = {
                val tryReturning = onReturn(fname, lname, userName, email, pwd, pwdVerification);
                if (tryReturning != "") {
                    errorText = "Passwords do not match. Try again"
                }
                 }) {
                Text("Register")
            }

            Text(errorText)
        }
    }

    @Composable
    fun TextFieldFormat(name: String, onNameChange: (String) -> Unit, title: String) {
        Column(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
        }
    }
}