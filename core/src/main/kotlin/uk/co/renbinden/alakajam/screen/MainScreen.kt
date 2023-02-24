package uk.co.renbinden.alakajam.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.gl
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import uk.co.renbinden.alakajam.actors.Sprite2D
import uk.co.renbinden.alakajam.actors.TextureFile
import uk.co.renbinden.alakajam.input.DelegatingInputProcessor

class MainScreen : ScreenAdapter() {

    private val stage = Stage(FitViewport(640f, 360f))
    private val hud = Stage(FitViewport(1280f, 720f))
    private val inputProcessor = DelegatingInputProcessor(hud, stage)

    init {
        val sprite = Sprite(TextureFile.WHITE)
        stage.addActor(
            Sprite2D(
                sprite,
                Vector2(320f, 180f),
                Vector2(10f, 10f)
            )
        )
    }

    override fun render(delta: Float) {
        act(delta)
        clear()
        draw(delta)
    }

    private fun act(delta: Float) {
        stage.act(delta)
        hud.act(delta)
        // any camera movement should happen here - stage.camera.position.set etc
    }

    private fun draw(delta: Float) {
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