// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import userinterface.LoginPage
import userinterface.RegisterPage

var register = RegisterPage();
var login = LoginPage();

@Composable
@Preview
fun App() {
    login.onSignUp = {SetPage(0)};
    login.RenderPage ();
    register.onReturn = {SetPage(1)}
    register.RenderPage ();

    SetPage(1)
}
fun SetPage(int: Int){
    CloseAllPages();
    if (int == 0){
        register.ShowPage()
    }
    if (int == 1){
        login.ShowPage()
    }
}

fun CloseAllPages(){
    login.ClosePage();
    register.ClosePage();
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
