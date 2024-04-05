package model

import userinterface.AudioBar
import userinterface.Video

class PlaylistManager {

    private lateinit var playlist: MutableList<Video>
    init {
        instance = this
        playlist = mutableListOf();
    }

    fun AddToQueue(video : Video) {
        playlist.add(video);
        println("Added " + video.title);
        UploadManager.instance?.updatePlaylist();
    }

    fun RemoveFromQueue(video : Video) {
        playlist.remove(video);
        println("Removed " + video.title);
        UploadManager.instance?.updatePlaylist();
    }

    fun GetPlaylist() : MutableList<Video> {
        return playlist;
    }

    public fun SetPlaylist(playlist : MutableList<Video> ) {
        this.playlist = playlist;
    }

     companion object {
         var instance: PlaylistManager? = null;
     }
}
