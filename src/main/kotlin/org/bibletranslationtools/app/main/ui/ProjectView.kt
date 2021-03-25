package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ProjectView : View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Project"
    override val pageIcon = FontIcon(MaterialDesign.MDI_BOOK)
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

        add(
            FontIcon(MaterialDesign.MDI_BOOK).also {
                it.iconSize = 100
            }
        )

        button("Go to Chapter 1 Page").apply {
            graphic = FontIcon(MaterialDesign.MDI_FILE)
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
