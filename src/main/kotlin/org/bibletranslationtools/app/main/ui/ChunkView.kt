package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ChunkView: View() {

    private val nameProperty = SimpleStringProperty()

    private val breadCrumb = BreadCrumb().apply {
        titleProperty.bind(this@ChunkView.nameProperty)
        activeTitleProperty.set("Chunk")
        iconProperty.set(FontIcon(MaterialDesign.MDI_BOOKMARK))
        onClickAction {
            navigator.dock(this@ChunkView, this)
        }
    }

    private val mainViewModel = find<MainViewModel>()
    private val navigator: Navigator by inject()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Chunk 1 Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(
            FontIcon(MaterialDesign.MDI_BOOKMARK).also {
                it.iconSize = 100
            }
        )

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go to Project Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_BOOK)
                setOnAction {
                    navigator.dock<ProjectView>()
                }
            }
            button("Go to Take 1 Page").apply {
                graphic = FontIcon(FontAwesomeSolid.WAVE_SQUARE)
                setOnAction {
                    mainViewModel.activeChunkProperty.set("Chunk 1")
                    navigator.dock<TakeView>()
                }
            }
        }
    }

    init {
        nameProperty.bind(mainViewModel.activeChunkProperty)
    }

    override fun onDock() {
        super.onDock()
        navigator.dock(this, breadCrumb)
    }
}
