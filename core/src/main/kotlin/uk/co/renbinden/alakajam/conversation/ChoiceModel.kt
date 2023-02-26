package uk.co.renbinden.alakajam.conversation

import com.bladecoder.ink.runtime.Story

class ChoiceModel(val story: Story, val index: Int, val text: String) {
    fun select() {
        story.chooseChoiceIndex(index)
    }
}