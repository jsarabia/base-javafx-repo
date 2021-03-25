package org.bibletranslationtools.app.main.ui

import org.kordamp.ikonli.javafx.FontIcon

enum class BreadcrumbType {
    PROJECT,
    CHAPTER,
    CHUNK,
    TAKE,
    RECORD
}

interface BreadcrumbComponent {
    val name: String
    val defaultName: String
    val graphic: FontIcon
    val type: BreadcrumbType
    val onClick: () -> Unit
}
