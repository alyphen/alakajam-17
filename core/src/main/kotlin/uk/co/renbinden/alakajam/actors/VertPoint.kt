package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import uk.co.renbinden.alakajam.map.InvalidMapException
import kotlin.math.roundToInt

class VertPoint(
    z: Int,
    val targetZ: Int
) : Actor(), ZYIndexed {

    companion object {
        fun createVertPoint(
            stage: Stage,
            mapObject: MapObject,
            z: Int
        ): VertPoint {
            val id = mapObject.properties["id", Int::class.java]
            val targetZ = mapObject.properties["target_z", Int::class.java]
                ?: throw InvalidMapException("Vert point $id does not have a target_z attribute set")
            val vertPoint = VertPoint(z, targetZ)
            vertPoint.extractPosition(mapObject)
            stage.addActor(vertPoint)
            return vertPoint
        }
    }

    override val zi = z
    override val yi
        get() = y.roundToInt()

}