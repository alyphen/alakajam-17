package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.graphics.Color
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
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import uk.co.renbinden.alakajam.conversation.ConversationModel
import uk.co.renbinden.alakajam.map.InvalidMapException
import uk.co.renbinden.alakajam.screen.MapScreen
import kotlin.math.roundToInt

class Npc(
    private val screen: MapScreen,
    val id: Int,
    private val texture: TextureRegion,
    val z: Int,
    private val conversation: ConversationModel
) : Actor(), Interactable, Collidable, ZYIndexed {

    companion object {
        fun createNpc(
            game: Alakajam17,
            screen: MapScreen,
            stage: Stage,
            mapObject: MapObject,
            z: Int,
            textureAtlas: TextureAtlas,
        ): Npc {
            val id = mapObject.properties["id", Int::class.java]
            val conversation = mapObject.properties["conversation", String::class.java]
                ?: throw InvalidMapException("NPC $id does not have a conversation attribute set")
            if (!game.assetManager.isLoaded(conversation)) {
                game.assetManager.load(conversation, Story::class.java)
                // Block until the story's finished loading.
                // This shouldn't take toooo long and we'd rather do this than try to run an unloaded convo
                // I don't want to fight the asset manager to get it to recognise story dependencies when
                // loading a map right now either.
                game.assetManager.finishLoading()
            }
            val texture = mapObject.properties["texture", String::class.java]
                ?: throw InvalidMapException("NPC $id does not have a texture attribute set")
            val npc = Npc(
                screen,
                id,
                textureAtlas.findRegion(texture),
                z,
                ConversationModel(game.assetManager.get(conversation))
            )
            npc.extractPosition(mapObject)
            stage.addActor(npc)
            return npc
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

    override val isSolid = true

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.color = Color(1f, 1f, 1f, parentAlpha)
        batch.draw(texture, x, y)
    }

    override fun interact() {
        screen.showConversation(conversation)
    }

    override fun collide() {

    }

}