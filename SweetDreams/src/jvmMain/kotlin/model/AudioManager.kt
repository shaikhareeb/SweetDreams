package model

import java.net.URL
import javax.sound.sampled.AudioSystem

// SOURCE CODE CITATION: This audio-playing code was inspired from the following online forum: https://slack-chats.kotlinlang.org/t/520128/is-there-an-api-to-play-audio-using-compose-desktop-i-got-an
class AudioManager {
    var clip = AudioSystem.getClip()
    var audioInputStream = AudioSystem.getAudioInputStream(Thread.currentThread().contextClassLoader.getResource("sample_wav_file.wav"))

    fun openClip(url: String) {
        clip.close()
        audioInputStream = AudioSystem.getAudioInputStream(URL(url))
        clip.open(audioInputStream)
    }

    fun startClip() {
        println("Try to start the current clip")
        clip.start()
    }

    fun playOneLoop() {
        clip.loop(0)
    }

    fun pauseClip() {
        println("Try to pause the current clip")
        clip.stop()
    }
}