package model

import userinterface.AudioBar
import userinterface.Video
import java.net.URL
import javax.sound.sampled.AudioSystem
import java.io.File
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl

// SOURCE CODE CITATION: This audio-playing code was inspired from the following online forum: https://slack-chats.kotlinlang.org/t/520128/is-there-an-api-to-play-audio-using-compose-desktop-i-got-an
class AudioManager {
//    var playlist = listOf(
//        "/Users/akshenjasikumar/Documents/Waterloo/CS346/Team-102-16/SweetDreams/src/jvmMain/Resources/LittleBaby.wav",
//        "/Users/akshenjasikumar/Documents/Waterloo/CS346/Team-102-16/SweetDreams/src/jvmMain/Resources/sample_wav_file.wav",
//    )
    private lateinit var playlist: MutableList<Video>
    private var currentTrackIndex = 0
    private var clip: Clip? = null
    private var pausePosition: Long = 0
    private var clipName: String = "Nothing is playing"

    public lateinit var OnPlay : () -> Unit
    init {
        AudioManager.instance = this
        playlist = mutableListOf();
        loadTrack(currentTrackIndex)
    }

    public fun GetCurrentVideo() : Video?{
        if (currentTrackIndex < playlist.size)
            return playlist[currentTrackIndex];
        return null;
    }

    public fun loadPlaylist(playlist: MutableList<Video>){
        this.playlist = playlist;
    }

    public fun loadSingleClip(video : Video){
        AudioBar.instance?.clear();
        this.playlist = mutableListOf(video);
    }

    public fun loadTrack(index: Int) {
        clipName = "Nothing is playing"
        clip?.close()
        if (index >= playlist.size)
            return;
        currentTrackIndex = index;
        println("Loading Track");

        try {
            clip = AudioSystem.getClip().apply {
                val audioInputStream = AudioSystem.getAudioInputStream(URL(playlist[index].bloburl))
                open(audioInputStream)
                println("Loaded Track " + playlist[index].title);
                clipName = playlist[index].title;
            }
        } catch (e : Exception){
            println(e);
            clipName = "Nothing is playing"
        }

    }

    public fun play() {
        AudioBar.instance?.UpdateText();
        clip?.let {
            if (!it.isRunning) {
                it.microsecondPosition = pausePosition
                it.start()
                OnPlay();
            }
        }
    }

    public fun pause() {
        AudioBar.instance?.UpdateText();
        clip?.let {
            if (it.isRunning) {
                pausePosition = it.microsecondPosition
                it.stop()
            }
        }
    }

    public fun stop() {
        clip?.stop()
        pausePosition = 0
        clip?.microsecondPosition = 0
    }

    public fun next() {
        if (currentTrackIndex < playlist.size - 1) {
            currentTrackIndex++
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        } else {
            currentTrackIndex = 0
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }

    public fun seek(position: Long) {
        pausePosition = position
        clip?.microsecondPosition = pausePosition
    }

    public fun previous() {
        if (currentTrackIndex > 0) {
            currentTrackIndex--
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        } else {
            currentTrackIndex = 0
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }
    public fun isTrackEnded(): Boolean {
        return currentPosition >= duration
    }

    public fun resetSlider(){
        currentTrackIndex = 0
        loadTrack(currentTrackIndex)
        pausePosition = 0
    }

    public fun playTrackAtIndex(index: Int) {
        if (index in playlist.indices) {
            currentTrackIndex = index
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }


    val isPlaying: Boolean
        get() = clip?.isRunning ?: false

    val getClipName: String
        get() = clipName;
    fun setClipName(newName: String) {
        clipName = newName
    }


    val currentPosition: Long
        get() = clip?.microsecondPosition ?: 0

    val duration: Long
        get() = clip?.microsecondLength ?: 0

    fun setVolume(volume: Float) {
        clip?.let {
            val gainControl = it.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            gainControl.value = volume
        }
    }

    companion object {
        var instance: AudioManager? = null;
    }
}