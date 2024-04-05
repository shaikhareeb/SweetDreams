package userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class SettingsPage: Page() {
    lateinit var navBar : NavBar
    lateinit var audioBar: AudioBar
    lateinit var getName: () -> String
    lateinit var getUsername: () -> String
    lateinit var getUploadedCount: () -> String
    lateinit var onDelete : (String) -> String
    lateinit var onReset : (String) -> String


    @Composable
    override fun Content() {
        var pwd by remember { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }
        Scaffold(
            bottomBar = {
                Spacer(modifier = Modifier.width(200.dp))
                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.1F)
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray)
                        .background(Color(0xFF93AEDE))
                        .padding(vertical = 8.dp, horizontal = 8.dp),
                ) {
                    audioBar.audioplayer()
                }
            }
        ) {


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
                    modifier = Modifier.fillMaxSize().background(Color(0xFF93AEDE)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("About You: ", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Name: " + getName(), style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Username: " + getUsername(), style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Number of Uploaded Audios: " + getUploadedCount(), style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        errorText = onReset(pwd)
                    }) {
                        Text("Generate Password Reset Link", color = Color.White)
                    }

                    TextFieldFormat(
                        name = pwd,
                        isPwd = true,
                        onNameChange = { if (it.length <= 50) pwd = it },
                        "Enter Password to Delete Account"
                    )
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        errorText = onDelete(pwd)
                    }) {
                        Text("Delete Account (Brings You to Login)", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    SelectionContainer {
                        Text(errorText)
                    }
                }
            }
        }
    }
}