package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.AccountManager
import model.AudioManager
import model.PlaylistManager
import java.awt.Desktop
import java.net.URL
import javax.imageio.ImageIO
import kotlin.random.Random


class LockPage: Page() {
    lateinit var onLogin: (username: String, pwd: String) -> Boolean

    lateinit var audioBar: AudioBar

    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = {
                Row(
                    modifier = Modifier
                        .alpha(0f)
                ) {
                    audioBar.audioplayer()
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color(0xFF93AEDE)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                var errorText by remember { mutableStateOf("") }

                if (AudioManager.instance?.GetCurrentVideo() != null) {
                    Text("Playing lullabies...", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(20.dp))
                    VideoCard(AudioManager.instance?.GetCurrentVideo()!!)
                } else {
                    Text("No lullabies playing!", style = MaterialTheme.typography.h6)
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldFormat(
                    name = username,
                    isPwd = false,
                    onNameChange = { if (it.length <= 50) username = it },
                    "Account Email"
                )
                TextFieldFormat(
                    name = pwd,
                    isPwd = true,
                    onNameChange = { if (it.length <= 50) pwd = it },
                    "Account Password"
                )
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    val tryLoggingIn = onLogin(username, pwd);
                    if (!tryLoggingIn) {
                        errorText = "Email/password combination is invalid. Try Again"
                    }
                }) {
                    Text("Unlock Application (Return to Explore)", color = Color.White)
                }

                Text(errorText)
            }
        }
        }

    private fun loadImage(url: String): ImageBitmap {
        return ImageIO.read(URL(url)).toComposeImageBitmap()
    }

    @Composable
    fun VideoCard(video: Video) {
        Card(
            modifier = Modifier.width(200.dp).padding(bottom = 16.dp), // Set width for the card
            backgroundColor = Color(0xFFF2F1FB),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Placeholder for video thumbnail
                Box(
                    modifier = Modifier.height(180.dp).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (video.thumbnail == "thumbnail" || video.thumbnail == "no_image") {
                        val randomNumber = (video.title.length % 13) + 1
                        Image(
                            painter = painterResource("img$randomNumber.webp"), // Replace with your image path
                            contentDescription = "Logo",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    } else {
                        Image(
                            bitmap = loadImage(video.thumbnail),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = video.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                //Text(text = video.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}