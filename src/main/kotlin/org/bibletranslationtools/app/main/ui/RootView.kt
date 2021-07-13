package org.bibletranslationtools.app.main.ui

import javafx.scene.control.Label
import javafx.scene.control.Tooltip
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class RootView : View() {

    val viewModel: RootViewModel by inject()

    init {
        importStylesheet(resources.get("/css/my.css"))
    }

    override val root = vbox {
        addClass("main")

        hbox {
            spacing = 20.0
            vbox {
                addClass("player")
                spacing = 20.0

                label("Playback") {
                    addClass("title")
                }
                combobox(viewModel.selectedPlayer, viewModel.players) {
                    addClass("dropdown")
                    cellFormat {
                        graphic = Label().apply {
                            text = it.name
                            graphic = FontIcon(MaterialDesign.MDI_PLAY)
                            tooltip = Tooltip(it.description)
                        }
                    }
                    selectionModel.selectedItemProperty().onChange {
                        it?.let {
                            viewModel.setPlayMixer(it)
                        }
                    }
                }

                hbox {
                    spacing = 10.0
                    button("Play", FontIcon(MaterialDesign.MDI_PLAY)) {
                        addClass("btn", "btn--primary")
                        action { viewModel.play() }
                        disableProperty().bind(
                            viewModel.isPlayingProperty
                                .or(viewModel.isRecordingProperty)
                                .or(viewModel.playerProperty.isNull)
                        )
                    }
                    button("Stop", FontIcon(MaterialDesign.MDI_STOP)) {
                        addClass("btn", "btn--secondary")
                        action { viewModel.stop() }
                        disableProperty().bind(viewModel.isPlayingProperty.not())
                    }
                }
            }

            vbox {
                addClass("recorder")
                spacing = 20.0

                label("Recording") {
                    addClass("title")
                }
                combobox(viewModel.selectedRecorder, viewModel.recorders) {
                    addClass("dropdown")
                    cellFormat {
                        graphic = Label().apply {
                            text = it.name
                            graphic = FontIcon(MaterialDesign.MDI_MICROPHONE)
                            tooltip = Tooltip(it.description)
                        }
                    }
                    selectionModel.selectedItemProperty().onChange {
                        it?.let {
                            viewModel.setRecordMixer(it)
                        }
                    }
                }

                hbox {
                    spacing = 10.0
                    button("Record", FontIcon(MaterialDesign.MDI_RECORD)) {
                        addClass("btn", "btn--primary", "btn--danger")
                        action { viewModel.record() }
                        disableProperty().bind(
                            viewModel.isRecordingProperty
                                .or(viewModel.isPlayingProperty)
                        )
                    }
                    button("Stop", FontIcon(MaterialDesign.MDI_STOP)) {
                        addClass("btn", "btn--secondary")
                        action { viewModel.stop() }
                        disableProperty().bind(viewModel.isRecordingProperty.not())
                    }
                }
            }
        }
    }
}
