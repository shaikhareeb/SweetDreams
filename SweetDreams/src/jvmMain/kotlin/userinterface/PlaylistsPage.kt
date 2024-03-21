package userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.AudioManager
import model.PlaylistManager
import okhttp3.HttpUrl.Companion.toHttpUrl
import youtube.youtubeData
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

// Assume Video data class and NavBar class are defined elsewhere
class PlaylistsPage : Page() {
    lateinit var navBar: NavBar
    lateinit var audioBar: AudioBar
    lateinit var thumbnails : Array<youtubeData?>
    lateinit var playAudio: (String) -> Unit

    init {
    }

    @Composable
    override fun Content() {

        val playlist = PlaylistManager.instance?.GetPlaylist();

        Column(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray).background(Color(0xFF93AEDE))) {

            Row(modifier = Modifier.fillMaxWidth()) {
                // NavBar on the left
                Column(
                    modifier = Modifier.width(200.dp).fillMaxHeight().border(2.dp, Color.Gray),
                    verticalArrangement = Arrangement.Top
                ) {
                    navBar.nav()
                }

                // Main content area for videos in a manually created scrollable grid
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState).background(Color(0x95b0df)),
                    verticalArrangement = Arrangement.Top,
                ) {
                    // Header
                    Text(
                        "Playlist",
                        style = MaterialTheme.typography.h4.copy(color = Color.White),
                        modifier = Modifier.padding(16.dp)

                    )
                    // Create rows for the grid
                    if (playlist != null) {
                        val numberOfRows = (playlist.size + 2) / 3 // Assuming 3 columns
                        for (rowIndex in 0 until numberOfRows) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                for (columnIndex in 0..3) {
                                    val videoIndex = rowIndex * 3 + columnIndex
                                    if (videoIndex < playlist.size) {
                                        VideoCard(video = playlist[videoIndex])
                                    } else {
                                        Spacer(
                                            modifier = Modifier.width(200.dp).padding(bottom = 16.dp)
                                        ) // Fill space if no video
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
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
                    modifier = Modifier.height(180.dp).fillMaxWidth().border(1.dp, Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = video.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = video.description, style = MaterialTheme.typography.body2)


                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                }) {
                    Text("Play Video", color = Color.White)
                }
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    PlaylistManager.instance?.RemoveFromQueue(video);
                }) {
                    Text("-", color = Color.White)
                }
            }
        }
    }
}
