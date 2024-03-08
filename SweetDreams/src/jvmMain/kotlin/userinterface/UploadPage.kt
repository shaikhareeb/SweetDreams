package userinterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class UploadPage: Page() {
    lateinit var navBar : NavBar
    @Composable
    override fun Content(){
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

        Row(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray)) {
            // NavBar on the left with its own outline
            Column(
                modifier = Modifier.width(200.dp).fillMaxHeight().border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Top
            ) {
                navBar.nav()
            }

            // Main content area on the right with its own outline
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Upload Page")
//                Text("Add a new lullaby from Youtube to your SweetDreams account")
//                var link by remember { mutableStateOf("") }
//                var tags by remember { mutableStateOf("") }
//                var errorText by remember { mutableStateOf("") }
//
//                TextFieldFormat(
//                    name = link,
//                    isPwd = false,
//                    onNameChange = { if (it.length <= 50) link = it },
//                    "Youtube Link"
//                )
//                TextFieldFormat(
//                    name = tags,
//                    isPwd = false,
//                    onNameChange = { if (it.length <= 50) tags = it },
//                    "Tags String (comma seperated)"
//                )
//                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
//                    val tryReturning = onSignup(link, tags);
//                    if (tryReturning != "") {
//                        errorText = tryReturning
//                    }
//                }) {
//                    Text("Add Youtube-Linked Lullaby", color = Color.White)
//                }
//
//                Text(errorText)
            }
        }
    }
}