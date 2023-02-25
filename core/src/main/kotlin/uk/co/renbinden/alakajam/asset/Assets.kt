package uk.co.renbinden.alakajam.asset

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader
import com.badlogic.gdx.maps.tiled.TiledMap
import com.bladecoder.ink.runtime.Story
import uk.co.renbinden.alakajam.conversation.StoryLoader

class Assets(private val assetManager: AssetManager) {

    private val loadedAssets = mutableListOf<Asset<out Any>>()

    init {
        assetManager.setLoader(TiledMap::class.java, AtlasTmxMapLoader(InternalFileHandleResolver()))
        assetManager.setLoader(Story::class.java, StoryLoader(InternalFileHandleResolver()))
    }

    fun switchAssets(requiredAssets: List<Asset<out Any>>) {
        val assetsToLoad = requiredAssets.toMutableList()
        assetsToLoad.removeAll(loadedAssets)

        assetsToLoad.forEach { asset ->
            Gdx.app.log(javaClass.simpleName, "Loading ${asset.fileName}")
            asset.load(assetManager)
            loadedAssets.add(asset)
        }

        val assetsToUnload = loadedAssets.toMutableList()
        assetsToUnload.removeAll(requiredAssets)

        assetsToUnload.forEach { asset ->
            Gdx.app.log(javaClass.simpleName, "Unloading ${asset.fileName}")
            asset.unload(assetManager)
            loadedAssets.remove(asset)
        }
    }

    operator fun <T> get(asset: Asset<T>): T {
        return assetManager.get(asset.fileName, asset.type)
    }

    fun isLoadingComplete(): Boolean {
        for (asset in loadedAssets) {
            if (!asset.isLoaded(assetManager)) {
                return false
            }
        }
        return true
    }

}