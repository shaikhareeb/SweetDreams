package AudioPlayerComponent// DesktopAudioPlayer.kt
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl

class DesktopAudioPlayer(private val playlist: List<String>) {
    private var currentTrackIndex = 0
    private var clip: Clip? = null
    private var pausePosition: Long = 0

    init {
        loadTrack(currentTrackIndex)
    }

    private fun loadTrack(index: Int) {
        clip?.close()
        clip = AudioSystem.getClip().apply {
            val audioInputStream = AudioSystem.getAudioInputStream(File(playlist[index]))
            open(audioInputStream)
        }
    }

    fun play() {
        clip?.let {
            if (!it.isRunning) {
                it.microsecondPosition = pausePosition
                it.start()
            }
        }
    }

    fun pause() {
        clip?.let {
            if (it.isRunning) {
                pausePosition = it.microsecondPosition
                it.stop()
            }
        }
    }

    fun stop() {
        clip?.stop()
        pausePosition = 0
        clip?.microsecondPosition = 0
    }

    fun next() {
        if (currentTrackIndex < playlist.size - 1) {
            currentTrackIndex++
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }

    fun seek(position: Long) {
        pausePosition = position
        clip?.microsecondPosition = pausePosition
    }

    fun previous() {
        if (currentTrackIndex > 0) {
            currentTrackIndex--
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }
    fun isTrackEnded(): Boolean {
        return currentPosition >= duration
    }

    fun playTrackAtIndex(index: Int) {
        if (index in playlist.indices) {
            currentTrackIndex = index
            loadTrack(currentTrackIndex)
            pausePosition = 0
            play()
        }
    }


    val isPlaying: Boolean
        get() = clip?.isRunning ?: false

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
}