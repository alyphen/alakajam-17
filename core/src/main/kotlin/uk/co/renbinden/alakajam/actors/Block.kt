package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import uk.co.renbinden.alakajam.behaviour.Collidable
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import kotlin.math.roundToInt

class Block(
    z: Int
) : Actor(), Collidable, ZYIndexed {

    companion object {
        fun createBlock(stage: Stage, mapObject: MapObject, z: Int): Block {
            val block = Block(
                z
            )
            block.extractPosition(mapObject)
            stage.addActor(block)
            return block
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

    override val isSolid: Boolean = true
    override fun collide() {

    }
}