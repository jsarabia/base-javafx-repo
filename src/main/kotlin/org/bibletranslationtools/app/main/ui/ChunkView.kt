package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ChunkView: View(), BreadcrumbComponent {

    private val nameProperty = SimpleStringProperty()

    override val name: String by nameProperty
    override val defaultName = "Chunk"
    override val graphic = FontIcon(MaterialDesign.MDI_BOOKMARK)
    override val type = BreadcrumbType.CHUNK
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

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
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Take 1 Page").apply {
                graphic = FontIcon(FontAwesomeSolid.WAVE_SQUARE)
                setOnAction {
                    mainViewModel.activeChunkProperty.set("Chunk 1")
                    workspace.dock<TakeView>()
                }
            }
        }
    }

    init {
        nameProperty.bind(mainViewModel.activeChunkProperty)
    }

    override fun onDock() {
        super.onDock()
        mainViewModel.addBreadcrumb(this)
        mainViewModel.removeBreadcrumbsAfter(this)
    }
}
