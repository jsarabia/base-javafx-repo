package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class PersonView: View(), BreadcrumbComponent {

    override val pageName = "Person"
    override val pageIcon = FontIcon("mdi-account")
    override val pageType = BreadcrumbType.PERSON
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Person Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-account:100"))

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go Home").apply {
                graphic = FontIcon("mdi-home")
                setOnAction {
                    workspace.dock<HomeView>()
                }
            }
            button("Go to Airplane Page").apply {
                graphic = FontIcon("mdi-airplane")
                setOnAction {
                    workspace.dock<AirplaneView>()
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
