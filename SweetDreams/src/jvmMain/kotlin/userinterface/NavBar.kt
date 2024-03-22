package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                .fillMaxHeight(0.9F)
                .background(Color(0xFF465E96)) // Dark purple background
                .padding(vertical = 8.dp), // Add some vertical padding for the column
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp)) // Adds space at the top

            Image(
                painter = painterResource("Picture1.png"), // Replace with your image path
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp) // Adjust the size as needed
                    .padding(bottom = 16.dp) // Add some space below the logo
            )

            Button(
                onClick = { onUser() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ) {
                Text("User", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onExplore() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Explore", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onUpload() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Upload", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onPlaylists() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Queue", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onSettings() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Settings", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onLock() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Lock Application", color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onLogout() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Log Out", color = Color.White)

            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}
