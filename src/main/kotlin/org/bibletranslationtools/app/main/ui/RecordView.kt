package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class RecordView: View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Record"
    override val pageIcon = FontIcon("mdi-microphone")
    override val pageType = BreadcrumbType.RECORD
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Record Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-microphone:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go to Project Page").apply {
                graphic = FontIcon("mdi-book")
                setOnAction {
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Chapter 1 Page").apply {
                graphic = FontIcon("mdi-file")
                setOnAction {
                    workspace.dock<ChapterView>()
                }
            }
        }
    }

    override fun onDock() {
        super.onDock()
        mainViewModel.addBreadcrumb(this)
        mainViewModel.removeBreadcrumbsAfter(this)
    }
}
