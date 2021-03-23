package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class AirplaneView: View(), BreadcrumbComponent {

    override val pageName = "Airplane"
    override val pageIcon = FontIcon("mdi-airplane")
    override val pageType = BreadcrumbType.AIRPLANE
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Airplane Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-airplane:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go Home").apply {
                graphic = FontIcon("mdi-home")
                setOnAction {
                    workspace.dock<HomeView>()
                }
            }
            button("Go to Book Page").apply {
                graphic = FontIcon("mdi-book")
                setOnAction {
                    workspace.dock<BookView>()
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
