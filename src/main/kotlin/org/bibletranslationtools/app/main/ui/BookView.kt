package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class BookView: View(), BreadcrumbComponent {

    override val pageName = "Book"
    override val pageIcon = FontIcon("mdi-book")
    override val pageType = BreadcrumbType.BOOK
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Book Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-book:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go Home").apply {
                graphic = FontIcon("mdi-home")
                setOnAction {
                    workspace.dock<HomeView>()
                }
            }
            button("Go to Person Page").apply {
                graphic = FontIcon("mdi-account")
                setOnAction {
                    workspace.dock<PersonView>()
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
