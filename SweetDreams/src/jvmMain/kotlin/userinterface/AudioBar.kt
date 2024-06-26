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
    var isQueuePlaying: Boolean = false
    var isPlaying: Boolean = false
    var textState : String = "Nothing is playing"

    init {
        instance = this;
    }

    private fun playQueue() {
        if (!isQueuePlaying) {
            var list = PlaylistManager.instance?.GetPlaylist();
            if (list != null && list.size != 0) {
                println("Now playing queue");
                isQueuePlaying = true
                textState = AudioManager.instance!!.getClipName;
                AudioManager.instance?.loadPlaylist(list)
                AudioManager.instance?.playTrackAtIndex(0);
            } else {
                println("Queue is empty");
            }
        } else {
            isQueuePlaying = false
            //AudioManager.instance?.setClipName("Nothing is playing")
            textState = "Nothing is playing";
            clear();
        }

    }

    fun clear(){
        pause();
        isQueuePlaying = false
        textState = "Nothing is playing";
        AudioManager.instance?.loadPlaylist(mutableListOf())
        AudioManager.instance?.loadTrack(0);
        AudioManager.instance?.resetSlider()
    }

    private fun pause(){
        AudioManager.instance?.pause();
        textState = AudioManager.instance!!.getClipName;
    }

    public fun play(){
        AudioManager.instance?.play();
        textState = AudioManager.instance!!.getClipName;
    }

    public fun UpdateText(){
        textState = AudioManager.instance!!.getClipName;
    }

    companion object {
        var instance: AudioBar? = null;
    }

    @Composable
    fun audioplayer() {
        var currentPosition by remember { mutableStateOf(0f) }

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
//                if (!isQueuePlaying and !isPlaying) textState = "Nothing is playing";
//                else textState = AudioManager.instance!!.getClipName;
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

            Row(modifier = Modifier.padding(horizontal = 200.dp), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = textState,
                    modifier = Modifier.absoluteOffset(x = (-75).dp),
                    style = MaterialTheme.typography.h6
                )
                Button(
                    onClick = {
                        if (isPlaying) pause();
                        else play();
                        isPlaying = !isPlaying;
                              },
                    //enabled = isQueuePlaying,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("⏯", color = Color.White)
                }

                Button(
                    onClick = { AudioManager.instance!!.previous(); play(); isPlaying = true },
                    //enabled = isQueuePlaying,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("⏮", color = Color.White)
                }

                Button(
                    onClick = { AudioManager.instance!!.next(); play(); isPlaying = true },
                    //enabled = isQueuePlaying,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(100.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text("⏭", color = Color.White)
                }

                Button(
                    onClick = { playQueue(); isPlaying = true; },
                    enabled = PlaylistManager.instance?.GetPlaylist()?.size != 0,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                    modifier = Modifier.width(150.dp).padding(horizontal = 8.dp).height(30.dp),
                    shape = RoundedCornerShape(8.dp) // Rounded corners
                ) {
                    Text(if (isQueuePlaying) "Stop Queue" else "Play Queue", color = Color.White)
                }
            }
        }
    }
}