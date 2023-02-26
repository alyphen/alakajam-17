package uk.co.renbinden.alakajam.conversation.function

import com.bladecoder.ink.runtime.Story
import uk.co.renbinden.alakajam.inventory.ItemType
import uk.co.renbinden.alakajam.save.Save

class GetItemCountFunction(private val save: Save) : Story.ExternalFunction1<String, Int>() {
    override fun call(itemTypeName: String?): Int {
        val itemType = itemTypeName?.let(ItemType::valueOf) ?: return 0
        return save.inventory.getAmount(itemType)
    }
}