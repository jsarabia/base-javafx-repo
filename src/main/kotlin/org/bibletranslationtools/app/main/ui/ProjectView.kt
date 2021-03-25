package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class ProjectView : View() {

    private val nameProperty = SimpleStringProperty()

    private val breadCrumb = BreadCrumb().apply {
        titleProperty.bind(this@ProjectView.nameProperty)
        activeTitleProperty.set("Project")
        iconProperty.set(FontIcon(MaterialDesign.MDI_BOOK))
        onClickAction {
            navigator.dock<ProjectView>()
        }
    }

    private val mainViewModel = find<MainViewModel>()
    private val navigator: Navigator by inject()

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
                navigator.dock<ChapterView>()
            }
        }
    }

    init {
        nameProperty.bind(mainViewModel.activeBookProperty)
    }

    override fun onDock() {
        super.onDock()
        navigator.dock(this, breadCrumb)
    }
}
