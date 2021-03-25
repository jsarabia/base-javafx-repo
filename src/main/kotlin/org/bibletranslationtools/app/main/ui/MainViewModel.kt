package org.bibletranslationtools.app.main.ui

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainViewModel: ViewModel() {

    val activeBookProperty = SimpleStringProperty()
    val activeChapterProperty = SimpleStringProperty()
    val activeChunkProperty = SimpleStringProperty()
    val activeTakeProperty = SimpleStringProperty()
}
