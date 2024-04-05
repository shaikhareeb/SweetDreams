package AudioPlayerComponent// Main.kt
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.input.key.Key
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App(audioPlayer: DesktopAudioPlayer) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0f) }

    // This coroutine updates the slider based on the current track's playback position
    LaunchedEffect(audioPlayer) {
        while (true) {
            currentPosition = (audioPlayer.currentPosition.toFloat() / audioPlayer.duration)
            if (audioPlayer.isTrackEnded() && isPlaying) {
                audioPlayer.next() // Play the next track if the current one has ended
                // Reset currentPosition for the new track
                currentPosition = 0f
            }
            delay(100L)
        }
    }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (!audioPlayer.isPlaying) {
                    audioPlayer.pause()
                    isPlaying = true
                }
                audioPlayer.previous()
            }) {
                Text("Previous")
            }
            Button(onClick = {
                if (audioPlayer.isPlaying) {
                    audioPlayer.pause()
                    isPlaying = false
                } else {
                    audioPlayer.play()
                    isPlaying = true
                }
            }) {
                Text(if (isPlaying) "Pause" else "Play")
            }
            Button(onClick = {
                if (!audioPlayer.isPlaying) {
                    audioPlayer.pause()
                    isPlaying = true
                }
                audioPlayer.next() })
            {
                Text("Next")
            }
        }
        Button(onClick = {
            audioPlayer.stop()
            isPlaying = false
            currentPosition = 0f
        }) {
            Text("Stop")
        }
        Slider(
            value = currentPosition,
            onValueChange = { newPosition ->
                currentPosition = newPosition
                val newPos = (newPosition * audioPlayer.duration).toLong()
                audioPlayer.seek(newPos)
            },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Compose Audio Player") {
        val playlist = listOf(
            "/Users/akshenjasikumar/Documents/Waterloo/CS346/Team-102-16/SweetDreams/src/jvmMain/Resources/LittleBaby.wav",
            "/Users/akshenjasikumar/Documents/Waterloo/CS346/Team-102-16/SweetDreams/src/jvmMain/Resources/sample_wav_file.wav",
        )

        val audioPlayer = remember { DesktopAudioPlayer(playlist) }
        App(audioPlayer)
    }
}