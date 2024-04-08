package userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class UserPage: Page() {
    lateinit var navBar : NavBar
    lateinit var audioBar: AudioBar
    lateinit var uploadedAudio: List<Video>
    lateinit var playAudio: (Video) -> Unit
    lateinit var deleteAudio: (Video) -> Unit
    lateinit var onAdd: (Video) -> Unit

    @Composable
    override fun Content() {
        // SOURCE CODE CITATION: The scaffolding structure throughout the project was inspired by discussion from this StackOverflow article: https://stackoverflow.com/questions/72081206/kotlin-jetpack-compose-how-to-remove-dp-or-sizefrom-the-modifier-fillmaxheight
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
        ) {
            Column(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray).background(Color(0xFF93AEDE))) {


            Row(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray)) {
                // NavBar on the left with its own outline
                Column(
                    modifier = Modifier.width(200.dp).fillMaxHeight().border(2.dp, Color.Gray),
                    verticalArrangement = Arrangement.Top
                ) {
                    navBar.nav()
                }

                // Main content area for videos in a manually created scrollable grid
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)
                        .background(Color(0xFF93AEDE)),
                    verticalArrangement = Arrangement.Top,
                ) {
                    // Header
                    Text(
                        "Your Lullabies",
                        style = MaterialTheme.typography.h4.copy(color = Color.White),
                        modifier = Modifier.padding(16.dp)

                    )
                    val numberOfRows = (uploadedAudio.size + 2) / 3
                    for (rowIndex in 0 until numberOfRows) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (columnIndex in 0..3) {
                                val videoIndex = rowIndex * 3 + columnIndex
                                if (videoIndex < uploadedAudio.size) {
                                    UploadedAudioCard(audio = uploadedAudio[videoIndex], playAudio, deleteAudio, onAdd, audioBar)
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