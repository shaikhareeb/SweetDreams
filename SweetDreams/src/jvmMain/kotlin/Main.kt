// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.AccountManager
import userinterface.LoginPage
import userinterface.MainPage
import userinterface.RegisterPage

var register = RegisterPage();
var login = LoginPage();
var main = MainPage();


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
    login.RenderPage ();
    register.onReturn = onReturn@{ s: String, s1: String, s2: String, s3: String, s4: String, s5: String ->
        val addUser = accountManager.addUser(s, s1, s2, s3, s4, s5);
        if (addUser == "") {
            SetPage(1)
        }
        return@onReturn addUser;
    }

    register.RenderPage();
    main.RenderPage()

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
}

fun CloseAllPages(){
    login.ClosePage();
    register.ClosePage();
    main.ClosePage();
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
