package uk.co.renbinden.alakajam.conversation.function

import com.bladecoder.ink.runtime.Story
import uk.co.renbinden.alakajam.conversation.ConversationFlag
import uk.co.renbinden.alakajam.save.Save

class GetConversationFlagFunction(private val save: Save) : Story.ExternalFunction1<String, Boolean>() {
    override fun call(flagName: String?): Boolean {
        val flag = flagName?.let(ConversationFlag::valueOf) ?: return false
        return save.conversationFlags[flag] ?: false
    }
}