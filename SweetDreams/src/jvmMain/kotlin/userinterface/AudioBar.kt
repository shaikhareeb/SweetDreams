package userinterface

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class AudioBar {
    lateinit var onPlay: () -> Unit
    lateinit var onPause: () -> Unit

    @Composable
    fun audioplayer() {
        Row(modifier = Modifier.padding(horizontal = 350.dp), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {onPlay()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.width(150.dp).padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ) {
                Text("Play", color = Color.White)
            }

            Button(
                onClick = {onPause()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8893D0)),
                modifier = Modifier.width(150.dp).padding(horizontal = 8.dp).height(60.dp),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ) {
                Text("Pause", color = Color.White)
            }
        }
    }
}