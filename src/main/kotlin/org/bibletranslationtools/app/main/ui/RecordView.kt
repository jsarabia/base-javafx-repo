package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign.MaterialDesign
import tornadofx.*

class RecordView: View(), BreadcrumbComponent {

    private val nameProperty = SimpleStringProperty()

    override val name: String by nameProperty
    override val defaultName = "Record"
    override val graphic = FontIcon(MaterialDesign.MDI_MICROPHONE)
    override val type = BreadcrumbType.RECORD
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
                    workspace.dock<ProjectView>()
                }
            }
            button("Go to Chapter 1 Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_FILE)
                setOnAction {
                    workspace.dock<ChapterView>()
                }
            }
            button("Go to No Record Page").apply {
                graphic = FontIcon(MaterialDesign.MDI_MICROPHONE_OFF)
                setOnAction {
                    workspace.dock<NoRecordView>()
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
