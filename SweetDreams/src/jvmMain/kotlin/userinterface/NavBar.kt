package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class NavBar {
    lateinit var onUser : () -> Unit
    lateinit var onExplore : () -> Unit
    lateinit var onUpload : () -> Unit
    lateinit var onPlaylists : () -> Unit
    lateinit var onSettings : () -> Unit
    @Composable
    fun nav() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Button(onClick = { onUser() }) {
                Text("User")
            }
            Button(onClick = { onExplore() }) {
                Text("Explore")
            }
            Button(onClick = { onUpload() }) {
                Text("Upload")
            }
            Button(onClick = { onPlaylists() }) {
                Text("Playlists")
            }
            Button(onClick = { onSettings() }) {
                Text("Settings")
            }
        }
    }
}