package userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class RegisterPage : Page() {
    lateinit var onReturn : () -> Unit
    @Composable
    override fun Content(){
        if (isPageVisible) {
            Text("suh");
            Button(onClick = { onReturn() }) {
                Text("Go back")
            }
        }
    }
}