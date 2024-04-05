package userinterface

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.AudioManager
import model.PlaylistManager
import model.UploadManager
import okhttp3.HttpUrl.Companion.toHttpUrl
import youtube.youtubeData
import java.awt.Desktop
import java.net.URL
import javax.imageio.ImageIO
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

// Assume Video data class and NavBar class are defined elsewhere
class PlaylistsPage : Page() {
    lateinit var navBar: NavBar
    lateinit var audioBar: AudioBar
//    lateinit var thumbnails : Array<youtubeData?>
    lateinit var playAudio: (Video) -> Unit
    lateinit var onDelete: (Video, Boolean) -> Unit

    init {
    }

    @Composable
    override fun Content() {
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
                            "Queue",
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
                    Image(
                        bitmap = loadImage(video.thumbnail),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = video.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                // Text(text = video.description, style = MaterialTheme.typography.body2)


                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    if (video.bloburl == "null") {
                        // SOURCE CODE CITATION: The code for opening links in the Desktop browser is provided by the following StackOverflow article: https://stackoverflow.com/questions/68306576/open-a-link-in-browser-using-compose-for-desktop
                        var desktop = Desktop.getDesktop()
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                            desktop.browse(URL("https://www.youtube.com/watch?v=${video.playerId}").toURI())
                        }
                    } else {
                        audioBar.isPlaying = true
                        playAudio(video)
                    }
                }) {
                    Text("Play Audio", color = Color.White)
                }
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    if (PlaylistManager.instance!!.GetPlaylist().size == 1) {
                        audioBar.isQueuePlaying = false
                        AudioManager.instance?.resetSlider()
                    }
                    onDelete(video, audioBar.isQueuePlaying)
                }) {
                    Text("-", color = Color.White)
                }
            }
        }
    }
}
