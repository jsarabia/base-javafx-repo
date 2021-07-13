package org.bibletranslationtools.app.audio

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Mixer
import javax.sound.sampled.SourceDataLine

class SimplePlayer(private val audio: File) {
    private val bufferSize = 4096

    private var player: SourceDataLine? = null
    private var mixer: Mixer.Info? = null
    private var isPlaying = false
    private var stopCallback: (() -> Unit)? = null

    fun play() {
        if (isPlaying) return
        isPlaying = true

        runCatching {
            val stream = AudioSystem.getAudioInputStream(audio)
            player = AudioSystem.getSourceDataLine(AUDIO_FORMAT, mixer)
            player?.let { _player ->
                Thread {
                    _player.open()
                    _player.start()

                    val bufferBytes = ByteArray(bufferSize)
                    var readBytes: Int

                    while (stream.read(bufferBytes).also { readBytes = it } != -1 && isPlaying) {
                        _player.write(bufferBytes, 0, readBytes)
                    }
                    stop()
                }.start()
            }
        }
            .onFailure {
                println(it)
                stop()
            }
    }

    fun stop() {
        if (!isPlaying) return
        isPlaying = false

        player?.let { _player ->
            _player.stop()
            _player.drain()
            _player.close()

            stopCallback?.invoke()
        }
    }

    fun setMixer(mixer: Mixer.Info) {
        this.mixer = mixer
    }

    fun onStop(callback: () -> Unit) {
        stopCallback = callback
    }
}
