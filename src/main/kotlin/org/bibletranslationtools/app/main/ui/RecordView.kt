package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class RecordView: View() {

    private val navigator: Navigator by inject()
    private val nameProperty = SimpleStringProperty()

    private val breadCrumb = BreadCrumb().apply {
        titleProperty.bind(this@RecordView.nameProperty)
        activeTitleProperty.set("Record")
        iconProperty.set(FontIcon(MaterialDesign.MDI_MICROPHONE))
        onClickAction {
            navigator.dock<RecordView>()
        }
    }

    override val root = vbox {
        spacing = 20.0
        paddingAll = 20.0
        alignment = Pos.CENTER

        label("Record Page") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }

        add(
            FontIcon(MaterialDesign.MDI_MICROPHONE).also {
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
            button("Go to Chapter 1 Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_FILE)
                setOnAction {
                    navigator.dock<ChapterView>()
                }
            }
            button("Go to No Record Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_MICROPHONE_OFF)
                setOnAction {
                    navigator.dock<NoRecordView>()
                }
            }
        }
    }

    override fun onDock() {
        super.onDock()
        navigator.dock(this, breadCrumb)
    }
}
