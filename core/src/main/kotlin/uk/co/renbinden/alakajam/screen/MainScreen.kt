package uk.co.renbinden.alakajam.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.gl
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import uk.co.renbinden.alakajam.Alakajam17
import uk.co.renbinden.alakajam.actors.SpinningSquare
import uk.co.renbinden.alakajam.actors.Text
import uk.co.renbinden.alakajam.asset.Fonts
import uk.co.renbinden.alakajam.asset.Textures
import uk.co.renbinden.alakajam.input.DelegatingInputProcessor

class MainScreen(private val game: Alakajam17) : ScreenAdapter() {

    companion object {
        val assets = listOf(
            Textures.textureAtlas,
            Fonts.lora32
        )
    }

    private val stage = Stage(FitViewport(640f, 360f))
    private val hud = Stage(FitViewport(1280f, 720f))
    private val inputProcessor = DelegatingInputProcessor(hud, stage)

    init {
        val textureAtlas = game.assets[Textures.textureAtlas]
        val sprite = Sprite(textureAtlas.findRegion("white"))
        val font = game.assets[Fonts.lora32]
        stage.addActor(
            SpinningSquare(
                sprite
            ).apply {
                setPosition(320f, 180f)
                setScale(0.2f, 0.2f)
            }
        )
        hud.addActor(
            Text(
                font,
                "Hello, world!"
            ).apply {
                setPosition(32f, 64f)
            }
        )
    }

    override fun render(delta: Float) {
        act(delta)
        clear()
        draw()
    }

    private fun act(delta: Float) {
        stage.act(delta)
        hud.act(delta)
        // any camera movement should happen here - stage.camera.position.set etc
    }

    private fun draw() {
        drawStage()
        drawHud()
    }

    private fun drawStage() = stage.draw()
    private fun drawHud() = hud.draw()

    private fun clear() {
        gl.glClearColor(0f, 0f, 0f, 1f)
        gl.glClear(GL_COLOR_BUFFER_BIT)
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