// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.AccountManager
import userinterface.LoginPage
import userinterface.MainPage
import userinterface.RegisterPage
import userinterface.PlaylistsPage
import userinterface.UploadPage
import userinterface.SettingsPage

var register = RegisterPage();
var login = LoginPage();
var main = MainPage();
var playlists = PlaylistsPage();
var upload = UploadPage();
var settings = SettingsPage();



@Composable
@Preview
fun App() {
    var accountManager = AccountManager()

    login.onSignUp = {SetPage(0)};
    login.onLogin = onReturn@{ s: String, s1: String ->
        val userLoginSuccess = accountManager.validateUser(s, s1)
        if (userLoginSuccess) {
            SetPage(2)
        }
        return@onReturn userLoginSuccess;
    }
    register.onReturn = onReturn@{ s: String, s1: String, s2: String, s3: String, s4: String, s5: String ->
        val addUser = accountManager.addUser(s, s1, s2, s3, s4, s5);
        if (addUser == "") {
            SetPage(1)
        }
        return@onReturn addUser;
    }
    register.onBack = {SetPage(1)}
    main.onSettings = {SetPage(3)}
    main.onPlaylists = {SetPage(4)}
    main.onUpload = {SetPage(5)}

    login.RenderPage ();
    register.RenderPage();
    main.RenderPage();
    playlists.RenderPage();
    upload.RenderPage();
    settings.RenderPage();

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
    if (int == 2) {
        main.ShowPage()
    }
    if (int == 3) {
        settings.ShowPage()
    }
    if (int == 4) {
        playlists.ShowPage()
    }
    if (int == 5) {
        upload.ShowPage()
    }
}

fun CloseAllPages(){
    login.ClosePage();
    register.ClosePage();
    main.ClosePage();
    settings.ClosePage();
    playlists.ClosePage();
    upload.ClosePage();
}


fun main() = application {
    Window(
        title = "SweetDreams",
        state = WindowState(width = 1024.dp, height = 768.dp),
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}
