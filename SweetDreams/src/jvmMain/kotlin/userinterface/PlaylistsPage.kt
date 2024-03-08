package userinterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class PlaylistsPage: Page() {
    lateinit var navBar : NavBar
    @Composable
    override fun Content() {
        Row(modifier = Modifier.fillMaxSize().border(2.dp, Color.Gray)) {
            // NavBar on the left with its own outline
            Column(
                modifier = Modifier.width(200.dp).fillMaxHeight().border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Top
            ) {
                navBar.nav()
            }

            // Main content area on the right with its own outline
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).border(2.dp, Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Playlists Page")
            }
        }
    }
}