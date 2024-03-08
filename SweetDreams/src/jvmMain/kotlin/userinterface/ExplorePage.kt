package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import okhttp3.internal.io.FileSystem

class ExplorePage: Page() {
    lateinit var navBar : NavBar
    @Composable
    override fun Content(){
        navBar.nav()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val isWhitenoise = remember { mutableStateOf(false) }
            val isYoutube = remember { mutableStateOf(false) }
            val isShort = remember { mutableStateOf(false) }

            Text("Youtube")
            Checkbox(
                checked = isYoutube.value ,
                enabled = true,
                onCheckedChange = {
                    isYoutube.value = it
                },
            )
            Text("Whitenoise")
            Checkbox(
                checked = isWhitenoise.value ,
                enabled = true,
                onCheckedChange = {
                    isWhitenoise.value = it
                },
            )
            Text("Short (less than 5 minutes)")
            Checkbox(
                checked = isShort.value ,
                enabled = true,
                onCheckedChange = {
                    isShort.value = it
                },
            )

            for (lullaby in currentLullabies) {
                var tags = lullaby.get(1).split(",")
                if (isWhitenoise.value && !("whitenoise" in tags)) {
                    continue;
                }
                if (isYoutube.value && !("youtube" in tags)) {
                    continue;
                }
                if (isShort.value && !("short" in tags)) {
                    continue;
                }
                Text(lullaby.get(0))
            }
        }
    }
}