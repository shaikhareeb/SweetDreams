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
    lateinit var onReturn : () -> Unit
    @Composable
    override fun Content(){
        var name by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign up for SweetDreams")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "First Name")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Last Name")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Username")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Email")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Password")
            TextFieldFormat(name = name, onNameChange = { if (it.length <= 50) name = it }, "Re-enter Password")
            Button(onClick = { onReturn() }) {
                Text("Register")
            }
        }
    }

    @Composable
    fun TextFieldFormat(name: String, onNameChange: (String) -> Unit, title: String) {
        Column(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
        }
    }
}