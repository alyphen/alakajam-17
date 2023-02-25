package uk.co.renbinden.alakajam

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale
import uk.co.renbinden.alakajam.asset.Assets
import uk.co.renbinden.alakajam.asset.Maps
import uk.co.renbinden.alakajam.screen.MapScreen
import uk.co.renbinden.alakajam.screen.container.ScreenContainer

class Alakajam17 : Game() {

    lateinit var assetManager: AssetManager
    lateinit var assets: Assets
    lateinit var screens: ScreenContainer

    override fun create() {
        VisUI.load(SkinScale.X1)
        assetManager = AssetManager()
        assets = Assets(assetManager)
        screens = ScreenContainer(this)
        val mapAsset = Maps.untitled
        assets.switchAssets(MapScreen.assets + mapAsset)
        screens.switchScreen { MapScreen(this, mapAsset) }
    }

    override fun dispose() {
        super.dispose()
        VisUI.dispose()
    }
}