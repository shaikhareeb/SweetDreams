package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

var currentLullabies = Array(1) { arrayOf("https://www.youtube.com/watch?v=2SmUkXtQIPc&ab_channel=BestBabyLullabies", "youtube,whitenoise") }

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
            for (lullaby in currentLullabies) {
                Text(lullaby.get(0))
            }
        }
    }
}