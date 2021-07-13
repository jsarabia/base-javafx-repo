package org.bibletranslationtools.app.audio

import java.io.File
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Mixer
import javax.sound.sampled.TargetDataLine

class SimpleRecorder(private val audio: File) {
    private var recorder: TargetDataLine? = null
    private var mixer: Mixer.Info? = null
    private var isRecording = false
    private var stopCallback: (() -> Unit)? = null

    fun record() {
        if (isRecording) return

        runCatching {
            recorder = AudioSystem.getTargetDataLine(AUDIO_FORMAT, mixer)
            recorder?.let { _recorder ->
                Thread {
                    isRecording = true
                    _recorder.open()
                    _recorder.start()

                    val stream = AudioInputStream(_recorder)
                    while (isRecording) {
                        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, audio)
                    }
                }.start()
            }
        }
            .onFailure {
                println(it)
            }
    }

    fun stop() {
        if (!isRecording) return
        recorder?.let { _recorder ->
            isRecording = false
            stopCallback?.invoke()
            _recorder.stop()
            _recorder.close()
        }
    }

    fun setMixer(mixer: Mixer.Info) {
        this.mixer = mixer
    }

    fun onStop(callback: () -> Unit) {
        stopCallback = callback
    }
}
