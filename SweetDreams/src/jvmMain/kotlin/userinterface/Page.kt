package userinterface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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
}