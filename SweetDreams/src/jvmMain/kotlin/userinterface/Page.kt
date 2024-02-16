package userinterface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

abstract class Page {
    var isPageVisible by mutableStateOf(false)
    fun ShowPage(){
        isPageVisible = true;
    }
    @Composable
    fun RenderPage(){
        if (isPageVisible){
            Content()
        }
    }
    @Composable
    abstract fun Content();
    fun ClosePage(){
        isPageVisible = false;
    }


    @Composable
    fun TextFieldFormat(name: String, isPwd: Boolean, onNameChange: (String) -> Unit, title: String) {
        if (isPwd) {
            Column(modifier = Modifier.padding(5.dp)) {
                OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) }, visualTransformation = PasswordVisualTransformation())
            }
        } else {
            Column(modifier = Modifier.padding(5.dp)) {
                OutlinedTextField(value = name, singleLine=true, onValueChange = onNameChange, label = { Text(title) })
            }
        }
    }
}