package uk.co.renbinden.alakajam.controls

import com.kotcrab.vis.ui.widget.VisDialog
import com.kotcrab.vis.ui.widget.VisLabel

class PressKeyDialog : VisDialog("Control") {
    init {
        contentTable.add(VisLabel("Press a key").apply { wrap = true }).width(360f)
        pack()
        centerWindow()
    }

}