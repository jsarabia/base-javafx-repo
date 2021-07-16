package org.bibletranslationtools.app.audio

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.io.File
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Mixer
import javax.sound.sampled.TargetDataLine
import org.bibletranslationtools.app.WavFileWriter
import org.wycliffeassociates.otter.common.audio.wav.WavFile
import org.wycliffeassociates.otter.common.audio.wav.WavOutputStream

class SimpleRecorder(private val audio: File) {
    private var recorder: TargetDataLine? = null
    private var mixer: Mixer.Info? = null

    @Volatile
    private var isRecording = false
    private var stopCallback: (() -> Unit)? = null
    private var audioByteObservable = PublishSubject.create<ByteArray>()


    fun record() {
        if (isRecording) return

        runCatching {
            recorder = AudioSystem.getTargetDataLine(AUDIO_FORMAT, mixer)
            recorder?.let { _recorder ->
                start(_recorder)
            }
        }
            .onFailure {
                println(it)
                stop()
            }
    }

    fun start(line: TargetDataLine) {
        audioByteObservable = PublishSubject.create<ByteArray>()
        line.open()
        line.start()
        Observable
            .fromCallable {
                isRecording = true
                val byteArray = ByteArray(2048)
                var totalRead = 0
                while (line.isOpen) {
                    totalRead += line.read(byteArray, 0, byteArray.size)
                    audioByteObservable.onNext(byteArray)
                }
            }
            .subscribeOn(Schedulers.io())
            .doOnError { e ->
                println("Error while recording audio")
            }
            .subscribe()
        audio.delete()
        audio.createNewFile()
        val wav = WavFile(audio, 1, 44100, 16)
        val writer = WavFileWriter(wav, audioByteObservable) {
            stop()
        }
        writer.start()
    }

    fun stop() {
        if (!isRecording) return

        recorder?.let { _recorder ->
            _recorder.stop()
            _recorder.close()

            isRecording = false
            stopCallback?.invoke()
            audioByteObservable.onComplete()
        }
    }

    fun setMixer(mixer: Mixer.Info) {
        this.mixer = mixer
    }

    fun onStop(callback: () -> Unit) {
        stopCallback = callback
    }
}
