package uk.co.renbinden.alakajam.asset

import com.badlogic.gdx.assets.AssetManager

class Asset<T>(
    val fileName: String,
    val type: Class<T>,
    val load: (assetManager: AssetManager) -> Unit = { assetManager -> assetManager.load(fileName, type) }
) {

    fun unload(assetManager: AssetManager) {
        assetManager.unload(fileName)
    }

    fun isLoaded(assetManager: AssetManager): Boolean {
        return assetManager.isLoaded(fileName)
    }

    fun get(assetManager: AssetManager): T {
        return assetManager.get(fileName, type)
    }
}