package userinterface

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .border(2.dp, Color.Gray)
                .background(Color(0xFF4A148C)) // Dark purple background
                .padding(vertical = 8.dp), // Add some vertical padding for the column
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Adds space at the top

            Button(
                onClick = { onUser() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ) {
                Text("User")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onExplore() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Explore")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onUpload() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Upload")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onPlaylists() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Playlists")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onSettings() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Settings")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onLock() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Lock Application")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onLogout() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(95.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Log Out")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
