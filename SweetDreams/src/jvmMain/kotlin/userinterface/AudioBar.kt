package userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class AudioBar {
    @Composable
    fun audioplayer() {
        Row(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .border(2.dp, Color.Gray)
                .background(Color(0xFF465E96)) // Dark purple background
                .padding(vertical = 8.dp, horizontal = 8.dp),
            content = {
                Text("Audio Player", color = Color.Black)
            }
        )
    }
}