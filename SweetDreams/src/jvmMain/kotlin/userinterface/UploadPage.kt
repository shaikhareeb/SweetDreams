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
    lateinit var onUpload: (audioFilePath: String, imageFilePath: String) -> Unit
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
                    var audioFilePath by remember { mutableStateOf("") }
                    var imageFilePath by remember { mutableStateOf("") }
                    var audioChooseOpen by remember { mutableStateOf(false) }
                    var imageChooseOpen by remember { mutableStateOf(false) }
                    var audioChooserOpened by remember { mutableStateOf(false) }
                    var imageChooserOpened by remember { mutableStateOf(false) }
                    var badAudioFileType by remember { mutableStateOf(false) }
                    var badImageFileType by remember { mutableStateOf(false) }

                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                        onClick = {
                            // Open file chooser for audio
                            audioChooseOpen = true
                        }
                    ) {
                        Text("Upload Audio from Desktop", color = Color.White)
                    }

                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                        onClick = {
                            // Open file chooser for image
                            imageChooseOpen = true
                        }
                    ) {
                        Text("Upload Image from Desktop", color = Color.White)
                    }

                    // SOURCE CODE CITATION: This code was taken/inspired by the following Java file chooser tutorial: https://www.geeksforgeeks.org/java-swing-jfilechooser/
                    if (audioChooseOpen) {
                        audioChooserOpened = true
                        val fileChooser = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)

                        val openValue: Int = fileChooser.showOpenDialog(null)
                        val allowedTypes = FileNameExtensionFilter("Audio files", "wav")
                        fileChooser.addChoosableFileFilter(allowedTypes)

                        if (openValue == JFileChooser.APPROVE_OPTION) {
                            audioFilePath = fileChooser.selectedFile.absolutePath
                            badAudioFileType = !audioFilePath.endsWith(".wav")
                        } else {
                            audioFilePath = ""
                        }
                        audioChooseOpen = false
                    }

                    if (audioFilePath == "" && audioChooserOpened) {
                        Text("You have not selected an audio file")
                    } else if (audioFilePath != "" && badAudioFileType) {
                        Text("The only currently supported audio file type is .wav")
                    } else if (audioFilePath != "") {
                        Text("Selected Audio File Path: $audioFilePath")
                    }

                    // Handle image file selection
                    if (imageChooseOpen) {
                        imageChooserOpened = true
                        val fileChooser = JFileChooser(FileSystemView.getFileSystemView().homeDirectory)

                        val openValue: Int = fileChooser.showOpenDialog(null)
                        val allowedTypes = FileNameExtensionFilter("Image files", "jpg")
                        fileChooser.addChoosableFileFilter(allowedTypes)

                        if (openValue == JFileChooser.APPROVE_OPTION) {
                            imageFilePath = fileChooser.selectedFile.absolutePath
                            badImageFileType = !imageFilePath.endsWith(".jpg")
                        } else {
                            imageFilePath = ""
                        }
                        imageChooseOpen = false
                    }

                    if (imageFilePath == "" && imageChooserOpened) {
                        Text("You have not selected an image file")
                    } else if (imageFilePath != "" && badImageFileType) {
                        Text("The only currently supported image file type is .jpg")
                    } else if (imageFilePath != "") {
                        Text("Selected Image File Path: $imageFilePath")
                    }

                    // Upload button should be enabled only if both audio and image are selected
                    val uploadEnabled = audioFilePath.isNotBlank() && imageFilePath.isNotBlank() && !badAudioFileType && !badImageFileType

                    Button(
                        enabled = uploadEnabled,
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (uploadEnabled) Color(0xFF8893D0) else Color.Gray),
                        onClick = {
                            onUpload(audioFilePath, imageFilePath)
                        }
                    ) {
                        Text("Upload", color = Color.White)
                    }
                }
            }
        }
    }
}