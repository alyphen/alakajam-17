package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.bladecoder.ink.runtime.Story
import uk.co.renbinden.alakajam.Alakajam17
import uk.co.renbinden.alakajam.behaviour.Collidable
import uk.co.renbinden.alakajam.behaviour.Interactable
import uk.co.renbinden.alakajam.behaviour.Stateful
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import uk.co.renbinden.alakajam.conversation.ConversationFlag
import uk.co.renbinden.alakajam.conversation.ConversationModel
import uk.co.renbinden.alakajam.inventory.ItemType
import uk.co.renbinden.alakajam.map.InvalidMapException
import uk.co.renbinden.alakajam.screen.MapScreen
import kotlin.math.roundToInt

class Item(
    override val id: Int,
    private val game: Alakajam17,
    private val screen: MapScreen,
    val z: Int,
    private val conversation: ConversationModel?,
    private val itemType: ItemType,
    private val amount: Int,
    private val animation: Animation<TextureRegion>,
    private val requiredFlags: List<ConversationFlag>
) : Actor(), Collidable, Interactable, Stateful, ZYIndexed {

    companion object {
        fun createItem(
            game: Alakajam17,
            screen: MapScreen,
            stage: Stage,
            mapName: String,
            mapObject: MapObject,
            z: Int,
            textureAtlas: TextureAtlas,
        ): Item {
            val id = mapObject.properties["id", Int::class.java]
            val conversation = mapObject.properties["conversation", String::class.java]
            if (conversation != null) {
                if (!game.assetManager.isLoaded(conversation)) {
                    game.assetManager.load(conversation, Story::class.java)
                    // Block until the story's finished loading, see createNPC.
                    game.assetManager.finishLoading()
                }
            }
            val itemTypeName = mapObject.properties["item_type", String::class.java]
                ?: throw InvalidMapException("Item $id has no item_type attribute set")
            val itemType = try {
                ItemType.valueOf(itemTypeName)
            } catch (exception: IllegalArgumentException) {
                throw InvalidMapException("Item $id has invalid item_type $itemTypeName", exception)
            }
            val amount = mapObject.properties["amount", Int::class.java] ?: 1

            val textureName = mapObject.properties["texture", String::class.java]
                ?: throw InvalidMapException("Item $id has no texture attribute set")

            val requiredFlags = mapObject.properties["required_flags", String::class.java]
                ?.split(",")
                ?.map { flagName ->
                    try {
                        ConversationFlag.valueOf(flagName)
                    } catch (exception: IllegalArgumentException) {
                        throw InvalidMapException("Item $id has invalid required flag: $flagName")
                    }
                }
                ?: emptyList()

            val item = Item(
                id,
                game,
                screen,
                z,
                if (conversation == null) null else ConversationModel(game.assetManager.get(conversation)),
                itemType,
                amount,
                Animation(0.2f, textureAtlas.findRegions(textureName), Animation.PlayMode.LOOP),
                requiredFlags
            )
            item.extractPosition(mapObject)
            item.loadState(game.save, mapName)
            stage.addActor(item)
            return item
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

    private var stateTime = 0f
    private var isClaimed: Boolean = false

    private val isRequirementsMet: Boolean
        get() = requiredFlags.any { flag -> game.save.conversationFlags[flag] == true }

    override val isSolid
        get() = !isClaimed && isRequirementsMet

    override fun collide() {

    }

    override fun interact() {
        if (isSolid) {
            isClaimed = false
            screen.updateMapState()
            game.save.inventory.add(itemType, amount)
            game.save.save()
            screen.showItemPopup(itemType, amount)
            if (conversation != null) {
                screen.showConversation(conversation)
            }
        }
    }

    override fun act(delta: Float) {
        super.act(delta)
        stateTime += delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (isSolid) {
            batch.color = Color(1f, 1f, 1f, parentAlpha)
            batch.draw(animation.getKeyFrame(stateTime), x, y)
        }
    }

    override var state: Map<String, Any>
        get() = mapOf("claimed" to !isClaimed)
        set(value) {
            isClaimed = !(value["claimed"] as? Boolean ?: false)
        }
}