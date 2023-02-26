package uk.co.renbinden.alakajam

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx.app
import com.badlogic.gdx.assets.AssetManager
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import uk.co.renbinden.alakajam.actors.Player.Companion.createPlayer
import uk.co.renbinden.alakajam.asset.Assets
import uk.co.renbinden.alakajam.asset.Maps
import uk.co.renbinden.alakajam.asset.Textures
import uk.co.renbinden.alakajam.map.InvalidMapException
import uk.co.renbinden.alakajam.save.Save
import uk.co.renbinden.alakajam.screen.MapScreen
import uk.co.renbinden.alakajam.screen.container.ScreenContainer

class Alakajam17 : Game() {

    lateinit var assetManager: AssetManager
    lateinit var assets: Assets
    lateinit var screens: ScreenContainer

    lateinit var save: Save

    override fun create() {
        save = Save.load() ?: Save()
        VisUI.load(SkinScale.X1)
        assetManager = AssetManager()
        assets = Assets(this, assetManager)
        screens = ScreenContainer(this)
        val mapAsset = when (save.playerPosition.map) {
            "maps/untitled.tmx" -> Maps.untitled
            else -> Maps.untitled
        }
        val playerX = save.playerPosition.x
        val playerY = save.playerPosition.y
        val playerZ = save.playerPosition.z
        assets.switchAssets(MapScreen.assets + mapAsset)
        screens.switchScreen {
            val mapScreen = try {
                MapScreen(this, mapAsset)
            } catch (exception: InvalidMapException) {
                app.error(javaClass.simpleName, "Failed to load map ${mapAsset.fileName}: ${exception.message}")
                throw exception
            }
            if (playerX != null && playerY != null && playerZ != null) {
                if (mapScreen.player != null) {
                    mapScreen.removePlayer()
                }
                createPlayer(
                    mapScreen,
                    mapScreen.stage,
                    assets[Textures.textureAtlas],
                    playerX,
                    playerY,
                    playerZ
                )
            }
            mapScreen
        }
    }

    override fun dispose() {
        super.dispose()
        VisUI.dispose()
    }
}