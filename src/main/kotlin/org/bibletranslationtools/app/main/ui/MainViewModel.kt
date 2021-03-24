package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainViewModel: ViewModel() {

    val breadcrumbs = observableListOf<BreadcrumbComponent>()
    val activeBookProperty = SimpleStringProperty()
    val activeChapterProperty = SimpleStringProperty()
    val activeChunkProperty = SimpleStringProperty()
    val activeTakeProperty = SimpleStringProperty()

    fun addBreadcrumb(page: BreadcrumbComponent) {
        if (breadcrumbs.contains(page)) return

        breadcrumbs.add(page)
    }

    fun removeBreadCrumb(page: BreadcrumbComponent) {
        breadcrumbs.remove(page)
    }

    fun removeBreadcrumbsAfter(page: BreadcrumbComponent) {
        val fromIndex = breadcrumbs.indexOf(page) + 1
        breadcrumbs.remove(fromIndex, breadcrumbs.size)
    }
}
