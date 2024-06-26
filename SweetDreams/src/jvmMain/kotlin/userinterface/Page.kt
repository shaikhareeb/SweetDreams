package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import java.net.URL
import javax.imageio.ImageIO
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale

abstract class Page {
    var currentLullabies = Array(1) { arrayOf("https://www.youtube.com/watch?v=2SmUkXtQIPc&ab_channel=BestBabyLullabies", "youtube,whitenoise") }

    var isPageVisible by mutableStateOf(false)
    fun ShowPage(){
        isPageVisible = true;
    }
    @Composable
    fun RenderPage(){
        if (isPageVisible){
            Content()
        }
    }
    @Composable
    abstract fun Content();
    fun ClosePage(){
        isPageVisible = false;
    }


    @Composable
    fun TextFieldFormat(name: String, isPwd: Boolean, onNameChange: (String) -> Unit, title: String) {
        if (isPwd) {
            Column(modifier = Modifier.padding(5.dp)) {
                OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) }, visualTransformation = PasswordVisualTransformation())
            }
        } else {
            Column(modifier = Modifier.padding(5.dp)) {
                OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
            }
        }
    }

    private fun loadImage(url: String): ImageBitmap {
        return ImageIO.read(URL(url)).toComposeImageBitmap()
    }

    @Composable
    fun UploadedAudioCard(audio: Video, playAudio: (Video) -> Unit, deleteAudio: (Video) -> Unit, onAdd: (Video) -> Unit, audioBar: AudioBar) {
        Card(
            modifier = Modifier.width(200.dp).padding(bottom = 16.dp), // Set width for the card
            backgroundColor = Color(0xFFF2F1FB),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Placeholder for audio thumbnail
                Box(
                    modifier = Modifier.height(180.dp).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        bitmap = loadImage(audio.thumbnail),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = audio.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))


                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    println("File played from Firebase Storage with URL: ${URL(audio.bloburl)}")
                    playAudio(audio)
                    audioBar.isPlaying = true
                }) {
                    Text("Play Audio", color = Color.White)
                }

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    deleteAudio(audio)
                }) {
                    Text("Delete Audio", color = Color.White)
                }

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    onAdd(audio)
                }) {
                    Text("+", color = Color.White)
                }
            }
        }
    }
}