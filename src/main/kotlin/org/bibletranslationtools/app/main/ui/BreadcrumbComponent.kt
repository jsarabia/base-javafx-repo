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
    var pageName: String
    val activePageName: String
    val pageIcon: FontIcon
    val pageType: BreadcrumbType
    val onClick: () -> Unit
}
