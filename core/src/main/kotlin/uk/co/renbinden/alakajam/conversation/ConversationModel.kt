package uk.co.renbinden.alakajam.conversation

import com.bladecoder.ink.runtime.Story

class ConversationModel(
        val story: Story
) {

    val isFinished: Boolean
        get() = !story.canContinue() && story.currentChoices.isEmpty()

    fun reset() {
        story.resetState()
    }

}