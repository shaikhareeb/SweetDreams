package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class UploadPage: Page() {
    lateinit var navBar : NavBar
    @Composable
    override fun Content(){
        navBar.nav()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Add a new lullaby from Youtube to your SweetDreams account")
            var link by remember { mutableStateOf("") }
            var tags by remember { mutableStateOf("") }

            TextFieldFormat(name = link, isPwd = false, onNameChange = { if (it.length <= 50) link = it }, "First Name")
            TextFieldFormat(name = tags, isPwd = false, onNameChange = { if (it.length <= 50) tags = it }, "Last Name")
        }
    }
}