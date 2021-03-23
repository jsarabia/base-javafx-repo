package org.bibletranslationtools.app.main.ui

import tornadofx.*

class RootView : View() {

    private val mainViewModel = find<MainViewModel>()

    init {
        importStylesheet(javaClass.getResource("/css/my.css").toExternalForm())
        workspace.root.header.hide()
    }

    override val root = vbox {
        add(BreadCrumbs(mainViewModel.breadcrumbs))
        add(
            workspace.root
        )
    }

    override fun onDock() {
        workspace.dock<HomeView>()
    }
}
