package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class TakeView: View() {

    private val nameProperty = SimpleStringProperty()

    private val breadCrumb = BreadCrumb().apply {
        titleProperty.bind(this@TakeView.nameProperty)
        activeTitleProperty.set("Take")
        iconProperty.set(FontIcon(FontAwesomeSolid.WAVE_SQUARE))
        onClickAction {
            navigator.dock(this@TakeView, this)
        }
    }

    private val mainViewModel = find<MainViewModel>()
    private val navigator: Navigator by inject()

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Take 1 Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(
            FontIcon(FontAwesomeSolid.WAVE_SQUARE).also {
                it.iconSize = 100
            }
        )

        hbox {
            spacing = 20.0
            alignment = Pos.CENTER

            button("Go to Project Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_BOOK)
                setOnAction {
                    navigator.dock<ProjectView>()
                }
            }
            button("Go to Record Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_MICROPHONE)
                setOnAction {
                    mainViewModel.activeTakeProperty.set("Take 1")
                    navigator.dock<RecordView>()
                }
            }
        }
    }

    init {
        nameProperty.bind(mainViewModel.activeTakeProperty)
    }

    override fun onDock() {
        super.onDock()
        navigator.dock(this, breadCrumb)
    }
}
