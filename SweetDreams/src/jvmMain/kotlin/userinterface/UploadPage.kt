package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class UploadPage: Page() {
    lateinit var navBar : NavBar
    @Composable
    override fun Content(){
        navBar.nav()
        fun onSignup(link: String, tags: String): String {
            if ("youtube.com" !in link) {
                return "Not a Youtube link"
            } else if (tags == "") {
                return "Please enter at least one tag"
            }
            for (lullaby in currentLullabies) {
                if (link == lullaby.get(0)) {
                    return "This Youtube link has already been uploaded to your account"
                }
            }

            currentLullabies += arrayOf(link, tags)
            return "Success!"
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Add a new lullaby from Youtube to your SweetDreams account")
            var link by remember { mutableStateOf("") }
            var tags by remember { mutableStateOf("") }
            var errorText by remember { mutableStateOf("") }

            TextFieldFormat(name = link, isPwd = false, onNameChange = { if (it.length <= 50) link = it }, "Youtube Link")
            TextFieldFormat(name = tags, isPwd = false, onNameChange = { if (it.length <= 50) tags = it }, "Tags String (comma seperated)")
            Button(onClick = {
                val tryReturning = onSignup(link, tags);
                if (tryReturning != "") {
                    errorText = tryReturning
                }
            }) {
                Text("Add Youtube-Linked Lullaby")
            }

            Text(errorText)
        }
    }
}