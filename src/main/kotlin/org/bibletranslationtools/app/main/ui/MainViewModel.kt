package org.bibletranslationtools.app.main.ui

import tornadofx.*

class MainViewModel: ViewModel() {

    val breadcrumbs = observableListOf<BreadcrumbComponent>()

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
