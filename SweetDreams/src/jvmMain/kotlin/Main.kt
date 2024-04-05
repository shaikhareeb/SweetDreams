// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.AccountManager
import model.AudioManager
import model.PlaylistManager
import model.UploadManager
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
var audiobar = AudioBar()
var currentPage = -1;

var playlistManager = PlaylistManager()


@Composable
@Preview
fun App() {
    model.Initialize();

    var accountManager = AccountManager()
    var uploadManager = UploadManager()
    var audioManager = AudioManager()

    explore.playAudio = onReturn@{video: Video ->
        AudioManager.instance?.loadSingleClip(video);
        AudioManager.instance?.loadTrack(0);
        AudioBar.instance?.play();
    }
    explore.deleteAudio = onReturn@{url: String ->
        uploadManager.deleteAudioFile(url, accountManager.getUser())

        val u = accountManager.getUser()
        val uploads = uploadManager.getAudioFiles(u)
        val bloblinks = uploadManager.getAudioLinks(u)
        explore.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
        user.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
        SetPage(2)
    }

    playlists.playAudio = onReturn@{video: Video ->
        AudioManager.instance?.loadSingleClip(video);
        AudioManager.instance?.loadTrack(0);
        AudioBar.instance?.play();
    }
    playlists.onDelete = onReturn@{video: Video ->
        PlaylistManager.instance?.RemoveFromQueue(video);
        SetPage(5)
    }
    user.playAudio = onReturn@{video: Video ->
        AudioManager.instance?.loadSingleClip(video);
        AudioManager.instance?.loadTrack(0);
        AudioBar.instance?.play();
    }
    user.deleteAudio = onReturn@{url: String ->
        uploadManager.deleteAudioFile(url, accountManager.getUser())

        val u = accountManager.getUser()
        val uploads = uploadManager.getAudioFiles(u)
        val bloblinks = uploadManager.getAudioLinks(u)
        explore.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
        user.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
        SetPage(3)
    }

    login.onSignUp = {SetPage(0)};
    login.onLogin = onReturn@{ s: String, s1: String ->
        val userLoginSuccess = accountManager.validateUser(s, s1)
        if (userLoginSuccess) {
            val u = accountManager.getUser()
            val uploads = uploadManager.getAudioFiles(u)
            val bloblinks = uploadManager.getAudioLinks(u)
            explore.uploadedAudio = uploads.mapIndexed { index, upload ->
                Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
            }
            user.uploadedAudio = uploads.mapIndexed { index, upload ->
                Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
            }
            SetPage(2)

            UploadManager.instance?.getPlaylist();
        }
        return@onReturn userLoginSuccess;
    }
    login.onForgotPwd = {mail: String -> accountManager.forgotPasswordEmail(mail)}
    register.onReturn = onReturn@{ s: String, s1: String, s2: String, s3: String, s4: String, s5: String ->
        val addUser = accountManager.addUser(s, s1, s2, s3, s4, s5);
        if (addUser == "") {
            SetPage(1)
        }
        return@onReturn addUser;
    }
    register.onBack = {SetPage(1)}

    settings.getName = onReturn@{
        return@onReturn accountManager.getUserDisplayName()
    }
    settings.getUsername = onReturn@{
        return@onReturn accountManager.getUsername()
    }
    settings.getUploadedCount = onReturn@{
        return@onReturn uploadManager.getAudioLinks(accountManager.getUser()).size.toString()
    }
    settings.onReset = onReturn@{pwd: String ->
        return@onReturn accountManager.forgotPassword()
    }
    settings.onDelete = onReturn@{uid: String ->
        var res = accountManager.deleteUser(uid)
        if (res == "") SetPage(1)
        return@onReturn res;
    }
    lockpage.onLogin = onReturn@{ s: String, s1: String ->
        val userLoginSuccess = accountManager.validateCurrentUser(s, s1)
        if (userLoginSuccess) {
            SetPage(2)
        }
        return@onReturn userLoginSuccess;
    }

    upload.onUpload = {filepath: String ->
        val u = accountManager.getUser()
        uploadManager.uploadAudioFile(filepath, u)
        val uploads = uploadManager.getAudioFiles(u)
        val bloblinks = uploadManager.getAudioLinks(u)
        explore.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
        user.uploadedAudio = uploads.mapIndexed { index, upload ->
            Video(index + 1, upload, "desc", "thumbnail", "playerid", 0, bloblinks.get(index)) // Assuming id starts from 1
        }
    }

    audiobar.onPlay = {
        //audioManager.startClip()
    }
    audiobar.onPause = {
        //audioManager.pauseClip()
    }

    audiobar.onPlayQueue = {
//        val playlist = PlaylistManager.instance?.GetPlaylist();
//        if (playlist != null) {
//            for (i in playlist.size - 1 downTo 0) {
//                audioManager.openClip(playlist[i].bloburl)
//                audioManager.startClip()
//            }
//        }

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

    lockpage.audioBar = audiobar
    explore.audioBar = audiobar
    user.audioBar = audiobar
    playlists.audioBar = audiobar
    upload.audioBar = audiobar
    settings.audioBar = audiobar

    login.RenderPage();
    register.RenderPage();
    explore.RenderPage();
    user.RenderPage();
    playlists.RenderPage();
    upload.RenderPage();
    settings.RenderPage();
    lockpage.RenderPage();

    SetPage(1)

    audioManager.OnPlay = { if (currentPage == 7) SetPage(7); };
}

fun SetPage(int: Int) {
    CloseAllPages();
    currentPage = int;
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
        state = WindowState(width = 1100.dp, height = 768.dp),
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}
