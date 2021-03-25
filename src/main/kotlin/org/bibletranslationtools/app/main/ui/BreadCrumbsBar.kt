package org.bibletranslationtools.app.main.ui

import javafx.scene.layout.HBox
import javafx.scene.paint.Paint
import tornadofx.*

class BreadCrumbsBar: HBox() {

    private val items = observableListOf<BreadCrumb>()

    init {
        spacing = 5.0

        style {
            padding = box(10.px)
            borderWidth += box(1.px)
            borderColor += box(Paint.valueOf("#ccc"))
        }

        items.onChange {
            children.clear()

            it.list.forEach { item ->
                item.isActiveProperty.set(false)
                children.add(item)
            }

            it.list.lastOrNull()?.isActiveProperty?.set(true)
        }
    }

    fun add(item: BreadCrumb) {
        if (items.contains(item).not()) {
            items.add(item)
        }
        removeAfter(item)
    }

    fun remove(item: BreadCrumb) {
        items.remove(item)
    }

    private fun removeAfter(item: BreadCrumb) {
        val fromIndex = items.indexOf(item) + 1
        items.remove(fromIndex, items.size)
    }
}
