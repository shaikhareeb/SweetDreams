package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainPage: Page() {
    lateinit var onSettings : () -> Unit
    lateinit var onPlaylists : () -> Unit
    lateinit var onUpload : () -> Unit
    @Composable
    override fun Content(){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("SweetDreams Application")
            Button(onClick = { onPlaylists() }) {
                Text("Playlists")
            }
            Button(onClick = { onUpload() }) {
                Text("Upload")
            }
            Button(onClick = { onSettings() }) {
                Text("Settings")
            }
        }
    }
}