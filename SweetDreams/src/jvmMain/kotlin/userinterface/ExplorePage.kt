package userinterface

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.RenderIntent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import model.AudioManager
import model.PlaylistManager
import okhttp3.HttpUrl.Companion.toHttpUrl
import youtube.youtubeData
import java.awt.Desktop
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.random.Random

// Assume Video data class and NavBar class are defined elsewhere
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String, // Placeholder for thumbnail URL or resource
    val playerId: String,
    val videoId: Int,
    val bloburl: String = "null"
)
class ExplorePage : Page() {
    lateinit var navBar: NavBar
    lateinit var audioBar: AudioBar
    lateinit var uploadedAudio: List<Video>
    lateinit var thumbnails : Array<youtubeData?>
    lateinit var playAudio: (Video) -> Unit
    lateinit var deleteAudio: (String) -> Unit

    // Sample list of videos
    lateinit var videos: MutableList<Video>

    init {
        //thumbnails = youtube.getSearchData("Lullabies");
        videos = mutableListOf();
        var index1 = 0;
//        for (data in thumbnails) {
//            var newVid = data?.let { Video(index1, it.title, data.description, data.thumbnail, data.playerId, data.id) };
//            index1 ++;
//            if (newVid != null) {
//                videos.add(newVid)
//            };
//        }

        var index = 0;
        for (i in 0..10) {
            var newVid = Video(index, "video", "blah", "blah", "blah", 0)
            index ++;
            if (newVid != null) {
                videos.add(newVid)
            };
        }

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
                                            Spacer(
                                                modifier = Modifier.width(200.dp).padding(bottom = 16.dp)
                                            ) // Fill space if no video
                                        }
                                    }

                                }
                            }
                        Text(
                            "",
                            modifier = Modifier.padding(16.dp))
//                        for (rowIndex in 0 until numberOfRows) {
//                            Row(
//                                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                for (columnIndex in 0..3) {
//                                    val videoIndex = rowIndex * 3 + columnIndex
//                                    if (videoIndex < uploadedAudio.size) {
//                                        UploadedAudioCard(audio = uploadedAudio[videoIndex], playAudio, deleteAudio)
//                                    } else {
//                                        Spacer(modifier = Modifier.width(200.dp).padding(bottom = 16.dp)) // Fill space if no video
//                                    }
//                                }
//                            }
//                        }
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
                    modifier = Modifier.height(180.dp).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val randomNumber = Random.nextInt(1, 14)
                    Image(
                        painter = painterResource("img$randomNumber.webp"), // Replace with your image path
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = video.title, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = video.description, style = MaterialTheme.typography.body2)


                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
                    // SOURCE CODE CITATION: The code for opening links in the Desktop browser is provided by the following StackOverflow article: https://stackoverflow.com/questions/68306576/open-a-link-in-browser-using-compose-for-desktop
                    var desktop = Desktop.getDesktop()
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(URL("https://www.youtube.com/watch?v=${video.playerId}").toURI())
                    }
                }) {
                    Text("Play Video", color = Color.White)
                }
//                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)), onClick = {
//                    PlaylistManager.instance?.AddToQueue(video);
//                }) {
//                    Text("+", color = Color.White)
//                }
            }
        }
    }
}
