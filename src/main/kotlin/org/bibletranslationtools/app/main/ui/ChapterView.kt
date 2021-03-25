package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ChapterView: View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Chapter"
    override val pageIcon = FontIcon(MaterialDesign.MDI_FILE)
    override val pageType = BreadcrumbType.CHAPTER
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

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
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Chunk 1 Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_BOOKMARK)
                setOnAction {
                    mainViewModel.activeChapterProperty.set("Chapter 1")
                    workspace.dock<ChunkView>()
                }
            }
        }
    }

    init {
        mainViewModel.activeChapterProperty.onChange {
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
