package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ChapterView: View() {

    private val nameProperty = SimpleStringProperty()

    private val breadCrumb = BreadCrumb().apply {
        titleProperty.bind(this@ChapterView.nameProperty)
        activeTitleProperty.set("Chapter")
        iconProperty.set(FontIcon(MaterialDesign.MDI_FILE))
        onClickAction {
            navigator.dock<ChapterView>()
        }
    }

    private val mainViewModel = find<MainViewModel>()
    private val navigator: Navigator by inject()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Chapter 1 Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(
            FontIcon(MaterialDesign.MDI_FILE).also {
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
            button("Go to Chunk 1 Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_BOOKMARK)
                setOnAction {
                    mainViewModel.activeChapterProperty.set("Chapter 1")
                    navigator.dock<ChunkView>()
                }
            }
        }
    }

    init {
        nameProperty.bind(mainViewModel.activeChapterProperty)
    }

    override fun onDock() {
        super.onDock()
        navigator.dock(this, breadCrumb)
    }
}
