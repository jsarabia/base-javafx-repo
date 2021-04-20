package org.bibletranslationtools.app.main.ui

import tornadofx.*

class RootView : View() {

    private val navigator: Navigator by inject()

    init {
        importStylesheet(javaClass.getResource("/css/my.css").toExternalForm())
        workspace.header.removeFromParent()
    }

    override val root = vbox {
        add(navigator.breadCrumbsBar)
        hbox {
            spacing = 10.0
            paddingAll = 10.0

            button("Back") {
                setOnAction {
                    navigator.back()
                }
            }
            button("Forward") {
                setOnAction {
                    navigator.forward()
                }
            }
        }
        add(workspace)
    }

    override fun onDock() {
        navigator.dock<ProjectView>()
    }
}
