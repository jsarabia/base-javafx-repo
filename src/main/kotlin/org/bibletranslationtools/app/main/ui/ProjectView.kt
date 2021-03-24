package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class ProjectView : View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Project"
    override val pageIcon = FontIcon("mdi-book")
    override val pageType = BreadcrumbType.PROJECT
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Project Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-book:100"))

        button("Go to Chapter 1 Page").apply {
            graphic = FontIcon("mdi-file")
            setOnAction {
                mainViewModel.activeBookProperty.set("Genesis")
                workspace.dock<ChapterView>()
            }
        }
    }

    init {
        mainViewModel.activeBookProperty.onChange {
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
