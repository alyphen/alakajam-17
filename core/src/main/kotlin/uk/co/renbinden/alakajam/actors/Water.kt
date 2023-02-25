package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import kotlin.math.roundToInt

class Water(z: Int) : Actor(), ZYIndexed {

    companion object {
        fun createWater(
            stage: Stage,
            mapObject: MapObject,
            z: Int
        ): Water {
            val water = Water(z)
            water.extractPosition(mapObject)
            stage.addActor(water)
            return water
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

}