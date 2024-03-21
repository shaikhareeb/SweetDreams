package model

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
    }

    fun RemoveFromQueue(video : Video) {
        playlist.remove(video);
        println("Removed " + video.title);
    }

    fun GetPlaylist() : MutableList<Video> {
        return playlist;
    }

     companion object {
         var instance: PlaylistManager? = null;
     }
}
