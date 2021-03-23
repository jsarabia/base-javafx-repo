package org.bibletranslationtools.app.main.ui

import org.kordamp.ikonli.javafx.FontIcon

enum class BreadcrumbType {
    HOME,
    BOOK,
    PERSON,
    AIRPLANE
}

interface BreadcrumbComponent {
    val pageName: String
    val pageIcon: FontIcon
    val pageType: BreadcrumbType
    val onClick: () -> Unit
}
