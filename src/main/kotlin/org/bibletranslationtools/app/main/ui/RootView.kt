package org.bibletranslationtools.app.main.ui

import com.dukescript.layouts.jfxflexbox.FlexBoxPane
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*

class RootView : View() {

    private val mainViewModel = find<MainViewModel>()

    init {
        importStylesheet(javaClass.getResource("/css/my.css").toExternalForm())
        workspace.root.header.hide()
    }

    override val root = vbox {
        add(BreadCrumbs(mainViewModel.breadcrumbs))
        add(
            workspace.root
        )
    }

    override fun onDock() {
        workspace.dock<HomeView>()
    }

    private fun makeLabel(
        txt: String = "Label",
        width: Double = 100.0,
        height: Double = 100.0,
        grow: Float = 1.0f,
        bg: Paint = Color.WHITE
    ): Label {

        return label {
            text = txt

            prefWidth = width
            maxWidth = Double.MAX_VALUE

            prefHeight = height
            maxHeight = Double.MAX_VALUE

            alignment = Pos.CENTER

            FlexBoxPane.setGrow(this, grow)
            FlexBoxPane.setMargin(this, Insets(5.0))

            style {
                backgroundColor += bg
            }
        }
    }
}
