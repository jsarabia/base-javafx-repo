package org.bibletranslationtools.app.main.ui

import tornadofx.*

class Navigator: Component(), ScopedInstance {

    val breadCrumbsBar = BreadCrumbsBar()

    inline fun <reified T : UIComponent> dock(breadCrumb: BreadCrumb? = null) {
        val view = find<T>()
        dock(view, breadCrumb)
    }

    fun dock(view: UIComponent, breadCrumb: BreadCrumb? = null) {
        breadCrumb?.let {
            breadCrumbsBar.addItem(it)
        }
        workspace.dock(view)
    }

    fun back() {
        workspace.navigateBack()
    }

    fun forward() {
        workspace.navigateForward()
    }
}
