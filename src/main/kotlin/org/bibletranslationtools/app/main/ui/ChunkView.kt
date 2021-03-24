package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class ChunkView: View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Chunk"
    override val pageIcon = FontIcon("mdi-bookmark")
    override val pageType = BreadcrumbType.CHUNK
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

        add(FontIcon("mdi-bookmark:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go to Project Page").apply {
                graphic = FontIcon("mdi-book")
                setOnAction {
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Take 1 Page").apply {
                graphic = FontIcon("fas-wave-square")
                setOnAction {
                    mainViewModel.activeChunkProperty.set("Chunk 1")
                    workspace.dock<TakeView>()
                }
            }
        }
    }

    init {
        mainViewModel.activeChunkProperty.onChange {
            it?.let {
                pageName = it
            }
        }
    }

    override fun onDock() {
        super.onDock()
        mainViewModel.addBreadcrumb(this)
        mainViewModel.removeBreadcrumbsAfter(this)
    }
}
