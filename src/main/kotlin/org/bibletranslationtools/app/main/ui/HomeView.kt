package org.bibletranslationtools.app.main.ui

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class HomeView : View(), BreadcrumbComponent {

    override val pageName = "Home"
    override val pageIcon = FontIcon("mdi-home")
    override val pageType = BreadcrumbType.HOME
    override val onClick = { workspace.dock(this) }

    private val mainViewModel = find<MainViewModel>()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Home Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(FontIcon("mdi-home:100"))

        button("Go to Book Page").apply {
            graphic = FontIcon("mdi-book")
            setOnAction {
                workspace.dock<BookView>()
            }
        }
    }

    override fun onDock() {
        super.onDock()
        mainViewModel.addBreadcrumb(this)
        mainViewModel.removeBreadcrumbsAfter(this)
    }
}
