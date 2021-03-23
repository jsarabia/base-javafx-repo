package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.layout.HBox
import javafx.scene.paint.Paint
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class BreadCrumb: HBox() {

    val iconProperty = SimpleObjectProperty<FontIcon>()
    val titleProperty = SimpleStringProperty()
    val isLastInQueueProperty = SimpleBooleanProperty(false)

    init {
        spacing = 5.0
        alignment = Pos.CENTER

        label {
            graphicProperty().bind(iconProperty)
            textProperty().bind(titleProperty)

            addClass("breadcrumb")

            isLastInQueueProperty.onChange {
                if (it) addClass("breadcrumb--active") else removeClass("breadcrumb--active")
            }
        }

        label {
            graphic = FontIcon("mdi-play")
            hiddenWhen(isLastInQueueProperty)
        }
    }
}
