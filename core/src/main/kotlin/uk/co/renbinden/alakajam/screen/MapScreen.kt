package uk.co.renbinden.alakajam.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import uk.co.renbinden.alakajam.Alakajam17
import uk.co.renbinden.alakajam.actors.Block.Companion.createBlock
import uk.co.renbinden.alakajam.actors.Player
import uk.co.renbinden.alakajam.actors.Player.Companion.createPlayer
import uk.co.renbinden.alakajam.actors.Water.Companion.createWater
import uk.co.renbinden.alakajam.asset.Asset
import uk.co.renbinden.alakajam.asset.Fonts
import uk.co.renbinden.alakajam.asset.Textures
import uk.co.renbinden.alakajam.behaviour.ZYIndexed
import uk.co.renbinden.alakajam.input.DelegatingInputProcessor
import uk.co.renbinden.alakajam.map.InvalidMapException
import uk.co.renbinden.alakajam.map.WorldMapLayer

class MapScreen(private val game: Alakajam17, private val mapAsset: Asset<TiledMap>) : ScreenAdapter() {

    companion object {
        val assets = listOf(
            Textures.textureAtlas,
            Fonts.lora32
        )
    }

    private val stage = Stage(FitViewport(640f, 360f))
    private val hud = Stage(FitViewport(1280f, 720f))
    private val inputProcessor = DelegatingInputProcessor(hud, stage)
    var player: Player? = null

    init {
        val textureAtlas = game.assets[Textures.textureAtlas]
        val map = game.assets[mapAsset]
        for (layer in map.layers) {
            stage.addActor(WorldMapLayer(map, layer, stage.batch, stage.camera as OrthographicCamera))

            val z = layer.properties["z", Int::class.java]
                ?: throw InvalidMapException("Layer ${layer.name} does not have a z attribute set")

            for (mapObject in layer.objects) {
                val id = mapObject.properties["id", Int::class.java]
                    ?: throw InvalidMapException("A map object on layer ${layer.name} does not have an ID")
                val type = mapObject.properties["type"]
                    ?: throw InvalidMapException("Map object $id does not have a type")
                when (type) {
                    "player" -> createPlayer(this, stage, mapObject, z, textureAtlas)
                    "water" -> createWater(stage, mapObject, z)
                    "block" -> createBlock(stage, mapObject, z)
                    else -> throw InvalidMapException("Map object $id has invalid type $type")
                }
            }
        }
    }

    override fun render(delta: Float) {
        act(delta)
        clear()
        draw()
    }

    private fun act(delta: Float) {
        stage.act(delta)
        hud.act(delta)
        stage.actors
            .filterIsInstance<ZYIndexed>()
            .sortedWith { a, b -> if (a.zi == b.zi) b.yi - a.yi else a.zi - b.zi }
            .filterIsInstance<Actor>()
            .forEach { it.zIndex = Int.MAX_VALUE }
        stage.camera.position.set(
            player?.x?.plus(player?.width?.div(2) ?: 0f) ?: 0f,
            player?.y?.plus(player?.height?.div(2) ?: 0f) ?: 0f,
            0f
        )
    }

    private fun draw() {
        drawStage()
        drawHud()
    }

    private fun drawStage() = stage.draw()
    private fun drawHud() = hud.draw()

    private fun clear() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun show() {
        addInputProcessor()
    }

    override fun hide() {
        removeInputProcessor()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
        hud.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
        hud.dispose()
    }

    private fun addInputProcessor() {
        Gdx.input.inputProcessor = inputProcessor
    }

    private fun removeInputProcessor() {
        Gdx.input.inputProcessor = null
    }

}