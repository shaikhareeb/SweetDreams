package userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView

class UploadPage: Page() {
    lateinit var navBar : NavBar
    lateinit var onUpload: (filepath: String) -> Unit
    lateinit var audioBar: AudioBar
    @Composable
    override fun Content() {
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
        )
        {


            Row(modifier = Modifier.fillMaxSize()) {
                // NavBar on the left with its own outline
                Column(
                    modifier = Modifier.width(200.dp).fillMaxHeight(),
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
                    Text("Upload Your Favourite Audios Here!", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Please make sure they are in .wav format", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(10.dp))
                    //Text("Upload Page")
                    //Text("Add a new lullaby from Youtube to your SweetDreams account")
                    var link by remember { mutableStateOf("") }
                    var tags by remember { mutableStateOf("") }
                    var errorText by remember { mutableStateOf("") }
                    var fileChooseOpen by remember { mutableStateOf(false) }

                    /*
                    TextFieldFormat(
                        name = link,
                        isPwd = false,
                        onNameChange = { if (it.length <= 50) link = it },
                        "Youtube Link"
                    )
                    TextFieldFormat(
                        name = tags,
                        isPwd = false,
                        onNameChange = { if (it.length <= 50) tags = it },
                        "Tags String (comma seperated)"
                    )
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        val tryReturning = onSignup(link, tags);
                        if (tryReturning != "") {
                            errorText = tryReturning
                        }
                    }) {
                        Text("Add Youtube-Linked Lullaby", color = Color.White)
                    }

                    Text(errorText)*/

                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                        fileChooseOpen = true
                    }) {
                        Text("Upload Local File from Desktop", color = Color.White)
                    }

                    var uploadFilePath by remember { mutableStateOf("") }
                    var fileChooserOpened by remember { mutableStateOf(false) }
                    var badFileType by remember { mutableStateOf(false) }

                    // SOURCE CODE CITATION: This code was taken/inspired by the following Java file chooser tutorial: https://www.geeksforgeeks.org/java-swing-jfilechooser/
                    if (fileChooseOpen) {
                        fileChooserOpened = true;
                        val fileChooser = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)

                        val openValue: Int = fileChooser.showOpenDialog(null);
                        val allowedTypes = FileNameExtensionFilter("Audio files", "mp3", "wav")
                        fileChooser.addChoosableFileFilter(allowedTypes)

                        if (openValue == JFileChooser.APPROVE_OPTION) {
                            uploadFilePath = fileChooser.selectedFile.absolutePath
                            if (uploadFilePath.endsWith(".wav")) {
                                onUpload(uploadFilePath)
                                badFileType = false
                            } else {
                                badFileType = true
                            }
                        } else {
                            uploadFilePath = ""
                        }
                        fileChooseOpen = false;
                    }

                    if (uploadFilePath == "" && fileChooserOpened) {
                        Text("You have not selected a file")
                    } else if (uploadFilePath != "" && badFileType) {
                        Text("The only currently supported file type is .wav")
                    } else if (uploadFilePath != "") {
                        Text("Selected File Path: " + uploadFilePath)
                    }
                }
            }
        }
    }
}