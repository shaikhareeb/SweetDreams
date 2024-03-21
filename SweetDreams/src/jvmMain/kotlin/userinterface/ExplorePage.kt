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
import okhttp3.HttpUrl.Companion.toHttpUrl
import youtube.youtubeData
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

// Assume Video data class and NavBar class are defined elsewhere
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String, // Placeholder for thumbnail URL or resource
    val bloburl: String = "null"
)
class ExplorePage : Page() {
    lateinit var navBar: NavBar
    lateinit var audioBar: AudioBar
    lateinit var uploadedAudio: List<Video>
    lateinit var thumbnails : Array<youtubeData?>
    lateinit var playAudio: (String) -> Unit

    // Sample list of videos
    private val videos = listOf(
        Video(1, "Video Title 1", "This is a description of video 1.", "thumbnail1.jpg"),
        Video(2, "Video Title 2", "This is a description of video 2.", "thumbnail2.jpg"),
        Video(3, "Video Title 3", "This is a description of video 3.", "thumbnail3.jpg"),
        Video(4, "Video Title 4", "This is a description of video 4.", "thumbnail4.jpg"),
        Video(5, "Video Title 5", "This is a description of video 5.", "thumbnail5.jpg"),
        Video(6, "Video Title 6", "This is a description of video 6.", "thumbnail6.jpg"),
        Video(7, "Video Title 7", "This is a description of video 7.", "thumbnail7.jpg"),
        Video(8, "Video Title 8", "This is a description of video 8.", "thumbnail8.jpg"),
        Video(9, "Video Title 9", "This is a description of video 9.", "thumbnail9.jpg"),
        Video(10, "Video Title 1", "This is a description of video 1.", "thumbnail1.jpg"),
        Video(11, "Video Title 2", "This is a description of video 2.", "thumbnail2.jpg"),
        Video(12, "Video Title 3", "This is a description of video 3.", "thumbnail3.jpg"),
        Video(13, "Video Title 4", "This is a description of video 4.", "thumbnail4.jpg"),
        Video(14, "Video Title 5", "This is a description of video 5.", "thumbnail5.jpg"),
        Video(15, "Video Title 6", "This is a description of video 6.", "thumbnail6.jpg"),
        Video(16, "Video Title 7", "This is a description of video 7.", "thumbnail7.jpg"),
        Video(17, "Video Title 8", "This is a description of video 8.", "thumbnail8.jpg"),
        Video(18, "Video Title 9", "This is a description of video 9.", "thumbnail9.jpg")
        // Add more videos as needed
    )

    init {
        thumbnails = youtube.getSearchData("Lullabies");
        //thumbnails = arrayOfNulls(0);
    }

    @Composable
    override fun Content() {
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
                        "Explore",
                        style = MaterialTheme.typography.h4.copy(color = Color.White),
                        modifier = Modifier.padding(16.dp)

                    )
                    // Create rows for the grid
                    val numberOfRows = (videos.size + 2) / 3 // Assuming 3 columns
                    for (rowIndex in 0 until numberOfRows) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (columnIndex in 0..3) {
                                val videoIndex = rowIndex * 3 + columnIndex
                                if (videoIndex < videos.size) {
                                    VideoCard(video = videos[videoIndex])
                                } else {
                                    Spacer(modifier = Modifier.width(200.dp).padding(bottom = 16.dp)) // Fill space if no video
                                }
                            }

                        }
                    }
                    Text(
                        "Uploads",
                        style = MaterialTheme.typography.h6.copy(color = Color.White),
                        modifier = Modifier.padding(16.dp)

                    )
                    for (rowIndex in 0 until numberOfRows) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (columnIndex in 0..3) {
                                val videoIndex = rowIndex * 3 + columnIndex
                                if (videoIndex < uploadedAudio.size) {
                                    UploadedAudioCard(audio = uploadedAudio[videoIndex], playAudio)
                                } else {
                                    Spacer(modifier = Modifier.width(200.dp).padding(bottom = 16.dp)) // Fill space if no video
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
                    if (video.id <= thumbnails.size) {
                        val youtubeData = thumbnails[video.id - 1];
                        if (youtubeData != null)
                            thumbnails[video.id - 1]?.let { Text(it.thumbnail) };
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (video.id <= thumbnails.size) {
                    thumbnails[video.id - 1]?.let {
                        Text(text = it.title, style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it.description, style = MaterialTheme.typography.body2)
                    };
                  } else {
                    Text(text = video.title, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = video.description, style = MaterialTheme.typography.body2)
                }

                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                }) {
                    Text("Play Video", color = Color.White)
                }
            }
        }
    }

    @Composable
    fun UploadedAudioCard(audio: Video, playAudio: (String) -> Unit) {
        Card(
            modifier = Modifier.width(200.dp).padding(bottom = 16.dp), // Set width for the card
            backgroundColor = Color(0xFFF2F1FB),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Placeholder for audio thumbnail
                Box(
                    modifier = Modifier.height(180.dp).fillMaxWidth().border(1.dp, Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = audio.thumbnail, style = MaterialTheme.typography.h6)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = audio.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = audio.description, style = MaterialTheme.typography.body2)


                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    println("File played from Firebase Storage with URL: ${URL(audio.bloburl)}")
                    playAudio(audio.bloburl)
                }) {
                    Text("Play Audio", color = Color.White)
                }
            }
        }
    }
}
