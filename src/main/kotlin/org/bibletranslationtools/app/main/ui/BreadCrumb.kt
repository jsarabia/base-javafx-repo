package org.bibletranslationtools.app.main.ui

import javafx.beans.binding.Bindings
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import org.kordamp.ikonli.javafx.FontIcon
import tornadofx.*

class BreadCrumb: HBox() {

    val iconProperty = SimpleObjectProperty<FontIcon>()
    val titleProperty = SimpleStringProperty()
    val activeTitleProperty = SimpleStringProperty()
    val isActiveProperty = SimpleBooleanProperty(false)

    init {
        spacing = 5.0
        alignment = Pos.CENTER

        label {
            graphicProperty().bind(iconProperty)
            textProperty().bind(titleBinding())

            addClass("breadcrumb")

            isActiveProperty.onChange {
                if (it) addClass("breadcrumb--active") else removeClass("breadcrumb--active")
            }
        }

        label {
            addClass("breadcrumb__arrow")

            graphic = FontIcon("mdi-play")
            hiddenWhen(isActiveProperty)
            managedWhen(visibleProperty())
        }

        label {
            addClass("breadcrumb__help")

            graphic = FontIcon("mdi-help-circle")
            visibleWhen(isActiveProperty)
            managedWhen(visibleProperty())
        }
    }

    private fun titleBinding(): StringBinding {
        return Bindings.createStringBinding(
            {
                if (isActiveProperty.value) {
                    activeTitleProperty.value
                } else {
                    titleProperty.value
                }
            },
            titleProperty,
            activeTitleProperty,
            isActiveProperty
        )
    }
}
