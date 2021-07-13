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

    val playerProperty = SimpleObjectProperty<SimplePlayer>()
    val recorderProperty = SimpleObjectProperty<SimpleRecorder>()

    val isPlayingProperty = SimpleBooleanProperty(false)
    val isRecordingProperty = SimpleBooleanProperty(false)

    private val audioDevices = AudioDevices()

    init {
        loadPlayMixers()
        loadRecordMixers()

        loadPlayer()
        loadRecorder()
    }

    private fun loadPlayMixers() {
        val mixers = audioDevices.getPlayers()
        players.setAll(mixers)
    }

    private fun loadRecordMixers() {
        val mixers = audioDevices.getRecorders()
        recorders.setAll(mixers)
    }

    fun setPlayMixer(mixer: Mixer.Info) {
        playerProperty.value?.let {
            it.stop()
            it.setMixer(mixer)
        }
    }

    fun setRecordMixer(mixer: Mixer.Info) {
        recorderProperty.value?.let {
            it.stop()
            it.setMixer(mixer)
        }
    }

    fun play() {
        playerProperty.value?.let { player ->
            isPlayingProperty.set(true)
            player.play()
            player.onStop {
                isPlayingProperty.set(false)
            }
        }
    }

    fun stop() {
        playerProperty.value?.stop()
        recorderProperty.value?.stop()
    }

    fun record() {
        recorderProperty.value?.let { recorder ->
            isRecordingProperty.set(true)
            recorder.record()
            recorder.onStop {
                isRecordingProperty.set(false)
                loadPlayer()
            }
        }
    }

    private fun loadRecorder() {
        if (recorderProperty.value == null) {
            val recording = File("recording.wav")
            val recorder = SimpleRecorder(recording)
            recorderProperty.set(recorder)
        }
    }

    private fun loadPlayer() {
        val audioFile = File("recording.wav")
        if (playerProperty.value == null && audioFile.exists()) {
            val player = SimplePlayer(audioFile)
            playerProperty.set(player)
        }
    }
}
