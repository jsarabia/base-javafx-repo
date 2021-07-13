package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import org.bibletranslationtools.app.audio.AudioDevices
import org.bibletranslationtools.app.audio.SimplePlayer
import org.bibletranslationtools.app.audio.SimpleRecorder
import tornadofx.*
import java.io.File
import javax.sound.sampled.Mixer

class RootViewModel: ViewModel() {

    val players = observableListOf<Mixer.Info>()
    val selectedPlayer = SimpleObjectProperty<Mixer.Info>()

    val recorders = observableListOf<Mixer.Info>()
    val selectedRecorder = SimpleObjectProperty<Mixer.Info>()

    private val playerProperty = SimpleObjectProperty<SimplePlayer>()
    private val recorderProperty = SimpleObjectProperty<SimpleRecorder>()
    private val recorderPlayerProperty = SimpleObjectProperty<SimplePlayer>()

    val isPlayingProperty = SimpleBooleanProperty(false)
    val isRecorderPlayingProperty = SimpleBooleanProperty(false)
    val isRecordingProperty = SimpleBooleanProperty(false)

    private val audioDevices = AudioDevices()

    init {
        loadPlayerList()
        loadRecorderList()

        loadPlayer()
        loadRecorder()
        loadRecorderPlayer()
    }

    private fun loadPlayerList() {
        val devices = audioDevices.getPlayers()
        players.setAll(devices)
    }

    private fun loadRecorderList() {
        val devices = audioDevices.getRecorders()
        recorders.setAll(devices)
    }

    fun setPlayer(mixer: Mixer.Info) {
        playerProperty.value?.let {
            it.stop()
            it.setMixer(mixer)
        }
    }

    fun setRecorder(mixer: Mixer.Info) {
        recorderProperty.value?.let {
            it.stop()
            it.setMixer(mixer)
        }
    }

    fun play() {
        isPlayingProperty.set(true)
        playerProperty.value?.play()
        playerProperty.value?.onStop {
            isPlayingProperty.set(false)
        }
    }

    fun stop() {
        playerProperty.value?.stop()
    }

    fun recordAudio() {
        isRecordingProperty.set(true)
        recorderProperty.value?.record()
        recorderProperty.value?.onStop {
            isRecordingProperty.set(false)
        }
    }

    fun stopRecording() {
        recorderProperty.value?.stop()
        recorderPlayerProperty.value?.stop()
        loadRecorderPlayer()
    }

    fun playRecording() {
        isRecorderPlayingProperty.set(true)
        recorderPlayerProperty.value?.play()
        recorderPlayerProperty.value?.onStop {
            isRecorderPlayingProperty.set(false)
        }
    }

    private fun loadRecorder() {
        val recording = File("recording.wav")
        val recorder = SimpleRecorder(recording)
        recorderProperty.set(recorder)
    }

    private fun loadRecorderPlayer() {
        val recording = File("recording.wav")
        if (recording.exists()) {
            val player = SimplePlayer(recording)
            recorderPlayerProperty.set(player)
        }
    }

    private fun loadPlayer() {
        val audioFile = File(resources.url("/audio/audio.wav").file)
        val player = SimplePlayer(audioFile)
        playerProperty.set(player)
    }
}
