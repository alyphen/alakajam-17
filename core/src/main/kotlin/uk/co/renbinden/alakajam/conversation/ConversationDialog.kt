package uk.co.renbinden.alakajam.conversation

import com.kotcrab.vis.ui.widget.VisDialog
import com.kotcrab.vis.ui.widget.VisLabel

class ConversationDialog(title: String, text: String, val controller: ConversationController, vararg val choices: ChoiceModel) : VisDialog(title) {

    init {
        contentTable.add(VisLabel(text).apply { wrap = true }).width(360f)
        if (choices.isNotEmpty()) {
            choices.forEachIndexed { index, choiceModel ->
                button(choiceModel.text, index).padBottom(3f)
            }
        } else {
            button("Next").padBottom(3f)
        }
        pack()
        centerWindow()
    }

    override fun result(`object`: Any?) {
        if (`object` is Int) {
            choices[`object`].select()
        }
        controller.progress()
    }
}