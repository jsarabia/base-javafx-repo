package org.bibletranslationtools.app.audio

import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.Mixer
import javax.sound.sampled.SourceDataLine
import javax.sound.sampled.TargetDataLine

internal val SAMPLE_RATE = 44100F
internal val SAMPLE_SIZE = 16
internal val CHANNELS = 1
internal val SIGNED = true
internal val BIG_ENDIAN = false
internal val AUDIO_FORMAT = AudioFormat(
    SAMPLE_RATE,
    SAMPLE_SIZE,
    CHANNELS,
    SIGNED,
    BIG_ENDIAN
)

class AudioDevices {
    private val mixers = AudioSystem.getMixerInfo()

    fun getPlayers(): List<Mixer.Info> {
        return mixers.filter { mixerInfo ->
            val mixer = AudioSystem.getMixer(mixerInfo)
            val playerInfo = DataLine.Info(SourceDataLine::class.java, AUDIO_FORMAT)
            val playerLines = mixer.getSourceLineInfo(playerInfo)
            playerLines.isNotEmpty()
        }.toList()
    }

    fun getRecorders(): List<Mixer.Info> {
        return mixers.filter { mixerInfo ->
            val mixer = AudioSystem.getMixer(mixerInfo)
            val recorderInfo = DataLine.Info(TargetDataLine::class.java, AUDIO_FORMAT)
            val recorderLines = mixer.getTargetLineInfo(recorderInfo)
            recorderLines.isNotEmpty()
        }.toList()
    }
}
