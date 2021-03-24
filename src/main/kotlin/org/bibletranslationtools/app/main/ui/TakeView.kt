package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class TakeView: View(), BreadcrumbComponent {

    override var pageName = ""
    override val activePageName = "Take"
    override val pageIcon = FontIcon("fas-wave-square")
    override val pageType = BreadcrumbType.TAKE
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Take 1 Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("fas-wave-square:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go to Project Page").apply {
                graphic = FontIcon("mdi-book")
                setOnAction {
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Record Page").apply {
                graphic = FontIcon("mdi-microphone")
                setOnAction {
                    mainViewModel.activeTakeProperty.set("Take 1")
                    workspace.dock<RecordView>()
                }
            }
        }
    }

    init {
        mainViewModel.activeTakeProperty.onChange {
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
