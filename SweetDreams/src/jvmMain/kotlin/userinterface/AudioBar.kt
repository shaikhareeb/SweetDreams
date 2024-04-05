package userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import model.AudioManager
import model.PlaylistManager

class AudioBar {
    lateinit var onPlay: () -> Unit
    lateinit var onPause: () -> Unit
    lateinit var onPlayQueue: () -> Unit

    init {
        instance = this;
    }

    private fun playQueue() {
        var list = PlaylistManager.instance?.GetPlaylist();
        if (list != null && list.size != 0) {
            println("Now playing queue");
            AudioManager.instance?.loadPlaylist(list)
            AudioManager.instance?.loadTrack(0);
            AudioManager.instance?.play();
        } else {
            println("Queue is empty");
        }
    }

    private fun pause(){
        AudioManager.instance?.pause();
    }

    public fun play(){
        AudioManager.instance?.play();
    }

    companion object {
        var instance: AudioBar? = null;
    }

    @Composable
    fun audioplayer() {
        var isPlaying by remember { mutableStateOf(false) }
        var currentPosition by remember { mutableStateOf(0f) }
        var textState by remember { mutableStateOf("Playing:") }

        LaunchedEffect(AudioManager.instance) {
            while (true) {
                if (AudioManager.instance != null) {;
                    val audioPlayer = AudioManager.instance!!;
                    if (audioPlayer.duration.toDouble() != 0.0) {
                        currentPosition = (audioPlayer.currentPosition.toFloat() / audioPlayer.duration)
                    }
                    if (audioPlayer.isTrackEnded() && isPlaying) {
                        audioPlayer.next()
                        currentPosition = 0f
                    }
                }
                delay(100L)
            }
        }
        Column {
            Slider(
                value = currentPosition,
                onValueChange = { newPosition ->
                    currentPosition = newPosition
                    val newPos = (newPosition * AudioManager.instance?.duration!!).toLong()
                    AudioManager.instance?.seek(newPos)
                },
                valueRange = 0f..1f,
                modifier = Modifier.fillMaxWidth().requiredHeight(25.dp),
            )
            Row(modifier = Modifier.padding(horizontal = 250.dp), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        if (isPlaying) pause();
                        else play();
                        isPlaying = !isPlaying;
                              },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("Play", color = Color.White)
                }

                Button(
                    onClick = { AudioManager.instance!!.previous(); play(); isPlaying = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("Prev", color = Color.White)
                }

                Button(
                    onClick = { AudioManager.instance!!.next(); play(); isPlaying = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("Next", color = Color.White)
                }

                Button(
                    onClick = { playQueue(); isPlaying = true; },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(150.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("Play Queue", color = Color.White)
                }
            }
        }
    }
}