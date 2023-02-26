package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import kotlin.math.roundToInt

class Underwater(z: Int) : Actor(), ZYIndexed {

    companion object {
        fun createUnderwater(
            stage: Stage,
            mapObject: MapObject,
            z: Int
        ): Underwater {
            val underwater = Underwater(z)
            underwater.extractPosition(mapObject)
            stage.addActor(underwater)
            return underwater
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

}