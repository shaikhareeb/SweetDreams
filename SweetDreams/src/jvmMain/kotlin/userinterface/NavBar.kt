package userinterface

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class NavBar {
    lateinit var onUser: () -> Unit
    lateinit var onExplore: () -> Unit
    lateinit var onUpload: () -> Unit
    lateinit var onPlaylists: () -> Unit
    lateinit var onSettings: () -> Unit
    lateinit var onLogout: () -> Unit
    lateinit var onLock: () -> Unit

    @Composable
    fun nav() {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().border(2.dp, Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onUser() }
            ) {
                Text("User")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onExplore() }
            ) {
                Text("Explore")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onUpload() }
            ) {
                Text("Upload")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onPlaylists() }
            ) {
                Text("Playlists")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onSettings() }
            ) {
                Text("Settings")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onLock() }
            ) {
                Text("Lock Application")
            }
            Button(
                modifier = Modifier.fillMaxWidth().weight(.5f),
                onClick = { onLogout() }
            ) {
                Text("Log Out")
            }
        }
    }
}
