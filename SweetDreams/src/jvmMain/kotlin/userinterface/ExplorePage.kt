package userinterface

import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ExplorePage : Page() {
    lateinit var navBar: NavBar

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
                val isWhitenoise = remember { mutableStateOf(false) }
                val isYoutube = remember { mutableStateOf(false) }
                val isShort = remember { mutableStateOf(false) }

                Text("Youtube")
                Checkbox(
                    checked = isYoutube.value,
                    enabled = true,
                    onCheckedChange = {
                        isYoutube.value = it
                    },
                )
                Text("Whitenoise")
                Checkbox(
                    checked = isWhitenoise.value,
                    enabled = true,
                    onCheckedChange = {
                        isWhitenoise.value = it
                    },
                )
                Text("Short (less than 5 minutes)")
                Checkbox(
                    checked = isShort.value,
                    enabled = true,
                    onCheckedChange = {
                        isShort.value = it
                    },
                )

                for (lullaby in currentLullabies) {
                    var tags = lullaby.get(1).split(",")
                    if (isWhitenoise.value && !("whitenoise" in tags)) {
                        continue
                    }
                    if (isYoutube.value && !("youtube" in tags)) {
                        continue
                    }
                    if (isShort.value && !("short" in tags)) {
                        continue
                    }
                    Text(lullaby.get(0))
                }
            }
        }
    }
}
