package uk.co.renbinden.alakajam.asset

import com.badlogic.gdx.maps.tiled.TiledMap

object Maps {

    val testMap1 = Asset(
        "maps/test_map_1.tmx",
        TiledMap::class.java
    )

    val untitled = Asset(
        "maps/untitled.tmx",
        TiledMap::class.java
    )

}