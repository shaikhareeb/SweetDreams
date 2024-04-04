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
    lateinit var onDelete : (String) -> String
    lateinit var onReset : (String) -> String


    @Composable
    override fun Content() {
        var sliderPosition = 100.toFloat()
        var uid by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }
        Scaffold(
            bottomBar = {
                Spacer(modifier = Modifier.width(200.dp))
                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.1F)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black)
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
                    modifier = Modifier.fillMaxSize().padding(16.dp).border(2.dp, Color.Gray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TextFieldFormat(
                        name = pwd,
                        isPwd = false,
                        onNameChange = { if (it.length <= 50) pwd = it },
                        "New Password"
                    )
                    TextFieldFormat(
                        name = uid,
                        isPwd = false,
                        onNameChange = { if (it.length <= 50) uid = it },
                        "Re-enter password to delete"
                    )
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        errorText = onDelete(uid)
                    }) {
                        Text("Delete Account", color = Color.White)
                    }
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        errorText = onReset(pwd)
                    }) {
                        Text("Reset Password", color = Color.White)
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