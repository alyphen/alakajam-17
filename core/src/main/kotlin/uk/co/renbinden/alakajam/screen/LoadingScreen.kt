package uk.co.renbinden.alakajam.screen

import com.badlogic.gdx.Gdx.gl
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.kotcrab.vis.ui.widget.VisProgressBar
import com.kotcrab.vis.ui.widget.VisTable
import uk.co.renbinden.alakajam.Alakajam17

class LoadingScreen(private val game: Alakajam17) : ScreenAdapter() {

    lateinit var onLoad: () -> Unit
    private var stateTime = 0f

    private val stage = Stage(FitViewport(640f, 360f))
    private val progressBar: VisProgressBar

    init {
        val table = VisTable()
        table.setFillParent(true)
        progressBar = VisProgressBar(0f, 1f, 0.01f, false)
        progressBar.setAnimateDuration(0.3f)
        progressBar.setAnimateInterpolation(Interpolation.pow4Out)
        table.add(progressBar)
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        act(delta)
        clear()
        draw()
    }

    private fun act(delta: Float) {
        stateTime += delta

        game.assetManager.update()
        progressBar.value = game.assetManager.progress
        stage.act(delta)

        if (game.assets.isLoadingComplete()) {
            onLoad.invoke()
        }
    }

    private fun draw() {
        stage.draw()
    }

    private fun clear() {
        gl.glClearColor(0f, 0f, 0f, 1f)
        gl.glClear(GL_COLOR_BUFFER_BIT)
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
    }

    override fun show() {
        stateTime = 0f
    }


}