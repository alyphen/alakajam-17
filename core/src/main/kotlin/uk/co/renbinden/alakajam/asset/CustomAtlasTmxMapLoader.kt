package uk.co.renbinden.alakajam.asset

import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader

class CustomAtlasTmxMapLoader : AtlasTmxMapLoader {

    constructor() : super()
    constructor(resolver: FileHandleResolver?) : super(resolver)



}