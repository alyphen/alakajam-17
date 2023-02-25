package uk.co.renbinden.alakajam.actors

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.scenes.scene2d.Actor
import uk.co.renbinden.alakajam.map.InvalidMapException

fun Actor.extractPosition(mapObject: MapObject) {
    val id = mapObject.properties["id", Int::class.java]
    x = mapObject.properties["x", Float::class.java]
        ?: throw InvalidMapException("Map object $id does not have an x attribute set")
    y = mapObject.properties["y", Float::class.java]
        ?: throw InvalidMapException("Map object $id does not have a y attribute set")
    width = mapObject.properties["width", Float::class.java]
        ?: throw InvalidMapException("Map object $id does not have a width attribute set")
    height = mapObject.properties["height", Float::class.java]
        ?: throw InvalidMapException("Map object $id does not have a height attribute set")
}