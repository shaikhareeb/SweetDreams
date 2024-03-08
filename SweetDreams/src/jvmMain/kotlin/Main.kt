// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.AccountManager
import userinterface.*

var register = RegisterPage();
var login = LoginPage();
var user = UserPage();
var explore = ExplorePage();
var upload = UploadPage();
var playlists = PlaylistsPage();
var settings = SettingsPage();
var lockpage = LockPage();
var navbar = NavBar()



@Composable
@Preview
fun App() {
    model.Initialize();

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

    settings.onReset = onReturn@{pwd: String ->
        SetPage(1)

    }
    settings.onDelete = {
        SetPage(1)
    }
    lockpage.onLogin = onReturn@{ s: String, s1: String ->
        val userLoginSuccess = accountManager.validateUser(s, s1)
        if (userLoginSuccess) {
            SetPage(2)
        }
        return@onReturn userLoginSuccess;
    }


    navbar.onExplore = {SetPage(2)}
    navbar.onUser = {SetPage(3)}
    navbar.onUpload = {SetPage(4)}
    navbar.onPlaylists = {SetPage(5)}
    navbar.onSettings = {SetPage(6)}
    navbar.onLock = {SetPage(7)}
    navbar.onLogout = {SetPage(1)}

    explore.navBar = navbar
    user.navBar = navbar
    playlists.navBar = navbar
    upload.navBar = navbar
    settings.navBar = navbar

    login.RenderPage ();
    register.RenderPage();
    explore.RenderPage();
    user.RenderPage();
    playlists.RenderPage();
    upload.RenderPage();
    settings.RenderPage();
    lockpage.RenderPage();

    SetPage(1)
}

fun SetPage(int: Int) {
    CloseAllPages();
    when (int) {
        0 -> register.ShowPage()
        1 -> login.ShowPage()
        2 -> explore.ShowPage()
        3 -> user.ShowPage()
        4 -> upload.ShowPage()
        5 -> playlists.ShowPage()
        6 -> settings.ShowPage()
        7 -> lockpage.ShowPage()
    }
}

fun CloseAllPages() {
    login.ClosePage();
    register.ClosePage();
    explore.ClosePage();
    user.ClosePage();
    upload.ClosePage();
    playlists.ClosePage();
    settings.ClosePage();
    lockpage.ClosePage();
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
